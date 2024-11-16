package br.edu.up.barberjet.model.user

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemoteRepository() : IRepository {

    var db = FirebaseFirestore.getInstance()
    var usuarioCollection = db.collection("usuario")

    override fun listarUsuarios(): Flow<List<Usuario>> = callbackFlow {
        val listener = usuarioCollection.addSnapshotListener {
                dados, erros ->
            if (erros != null){
                close(erros)
                return@addSnapshotListener
            }
            if (dados != null){
                val usuarios = dados.documents.mapNotNull { dado ->
                    dado.toObject(Usuario::class.java)
                }
                trySend(usuarios).isSuccess
            }
        }
        awaitClose{ listener.remove()}
    }

    suspend fun getId(): Int{
        val dados = usuarioCollection.get().await()

        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun gravarUsuario(usuario: Usuario) {
        val document: DocumentReference
        if (usuario.id == null){ // Inclusão
            usuario.id = getId()
            document = usuarioCollection.document(usuario.id.toString())
        } else { // Alteração
            document = usuarioCollection.document(usuario.id.toString())
        }
        document.set(usuario).await()
    }

    override suspend fun buscarUsuarioId(idx: Int): Usuario? {
        val dados = usuarioCollection.document(idx.toString()).get().await()

        return dados.toObject(Usuario::class.java)
    }

    override suspend fun excluirUsuario(usuario: Usuario) {
        usuarioCollection.document(usuario.id.toString()).delete().await()
    }
}
