package br.edu.up.barberjet.model.user

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun listarUsuarios(): Flow<List<Usuario>>
    suspend fun buscarUsuarioId(idx: Int): Usuario?
    suspend fun gravarUsuario(usuario: Usuario)
    suspend fun excluirUsuario(usuario: Usuario)
}
