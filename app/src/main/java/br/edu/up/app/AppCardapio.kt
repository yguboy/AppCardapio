package br.edu.up.app

import android.app.Application
import android.content.Context
import br.edu.up.app.data.BancoSQLite
import br.edu.up.app.data.ProdutoDAO
import br.edu.up.app.data.ProdutoRepository
import br.edu.up.app.data.ProdutoRepositorySqlite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class AppCardapio : Application() {

    @Provides
    fun provideProdutoRepository(produtoDAO: ProdutoDAO) : ProdutoRepository {
        return ProdutoRepositorySqlite(produtoDAO)
    }

    @Provides
    fun provideProdutoDAO(bancoSQLite: BancoSQLite): ProdutoDAO {
        return bancoSQLite.produtoDao()
    }

    @Provides
    @Singleton
    fun provideBanco(@ApplicationContext ctx: Context): BancoSQLite {
        return BancoSQLite.get(ctx)
    }
}