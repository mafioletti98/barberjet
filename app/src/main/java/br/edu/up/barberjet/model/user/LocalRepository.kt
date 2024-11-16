package br.edu.up.barberjet.model.user

import kotlinx.coroutines.flow.Flow

class LocalRepository(
    private val dao : UsuarioDAO
) : IRepository {

    override fun listarUsuarios(): Flow<List<Usuario>> {
        return dao.listarUsuarios()
    }

    override suspend fun buscarUsuarioId(idx: Int): Usuario {
        return dao.buscarUsuarioId(idx)
    }

    override suspend fun gravarUsuario(usuario: Usuario) {
        dao.gravarUsuario(usuario)
    }

    override suspend fun excluirUsuario(usuario: Usuario) {
        dao.excluirUsuario(usuario)
    }

}
