package br.edu.up.barberjet.model.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDAO{

    @Query("select * from usuario")
    fun listarUsuarios(): Flow<List<Usuario>>

    @Query("select * from usuario where id = :idx")
    suspend fun buscarUsuarioId(idx: Int): Usuario

    @Upsert
    suspend fun gravarUsuario(usuario: Usuario)

    @Delete
    suspend fun excluirUsuario(usuario: Usuario)

}