package pmdm.bloggerapi.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pmdm.bloggerapi.articulos.data.network.ArticuloClient
import pmdm.bloggerapi.clientes.data.network.ClienteClient
import pmdm.bloggerapi.clientes.data.network.LineaVentaClient
import pmdm.bloggerapi.clientes.data.network.PedidoClient
import pmdm.bloggerapi.utils.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideArticuloClient(retrofit: Retrofit): ArticuloClient {
        return retrofit.create(ArticuloClient::class.java)

    }
    @Provides
    @Singleton
    fun provideClienteClient(retrofit: Retrofit): ClienteClient {
        return retrofit.create(ClienteClient::class.java)
    }

    @Provides
    @Singleton
    fun providePedidoClient(retrofit: Retrofit): PedidoClient {
        return retrofit.create(PedidoClient::class.java)
    }

    @Provides
    @Singleton
    fun provideLineaVentaClient(retrofit: Retrofit): LineaVentaClient {
        return retrofit.create(LineaVentaClient::class.java)
    }

}