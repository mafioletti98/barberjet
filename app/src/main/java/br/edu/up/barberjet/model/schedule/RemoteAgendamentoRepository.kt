package br.edu.up.barberjet.model.schedule

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemoteAgendamentoRepository() : IAgendamentoRepository {

    var db = FirebaseFirestore.getInstance()
    var agendamentoCollection = db.collection("agendamento")

    override fun listarAgendamentos(): Flow<List<Agendamento>> = callbackFlow {
        val listener = agendamentoCollection.addSnapshotListener {
                dados, erros ->
            if (erros != null){
                close(erros)
                return@addSnapshotListener
            }
            if (dados != null){
                val agendamentos = dados.documents.mapNotNull { dado ->
                    dado.toObject(Agendamento::class.java)
                }
                trySend(agendamentos).isSuccess
            }
        }
        awaitClose{ listener.remove()}
    }

    suspend fun getId(): Int{
        val dados = agendamentoCollection.get().await()

        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun gravarAgendamento(agendamento: Agendamento) {
        val document: DocumentReference
        if (agendamento.id == null){ // Inclusão
            agendamento.id = getId()
            document = agendamentoCollection.document(agendamento.id.toString())
        } else {
            document = agendamentoCollection.document(agendamento.id.toString())
        }
        document.set(agendamento).await()
    }

    override suspend fun buscarAgendamentoId(idx: Int): Agendamento? {
        val dados = agendamentoCollection.document(idx.toString()).get().await()

        return dados.toObject(Agendamento::class.java)
    }

    override suspend fun excluirAgendamento(agendamento: Agendamento) {
        agendamentoCollection.document(agendamento.id.toString()).delete().await()
    }
}
