package com.example.favourite;

import com.example.core.domain.usecase.GameUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("com.example.androidexpertcapstone.di.AppScope")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class FavouriteViewModelFactory_Factory implements Factory<FavouriteViewModelFactory> {
  private final Provider<GameUseCase> gameUseCaseProvider;

  public FavouriteViewModelFactory_Factory(Provider<GameUseCase> gameUseCaseProvider) {
    this.gameUseCaseProvider = gameUseCaseProvider;
  }

  @Override
  public FavouriteViewModelFactory get() {
    return newInstance(gameUseCaseProvider.get());
  }

  public static FavouriteViewModelFactory_Factory create(
      Provider<GameUseCase> gameUseCaseProvider) {
    return new FavouriteViewModelFactory_Factory(gameUseCaseProvider);
  }

  public static FavouriteViewModelFactory newInstance(GameUseCase gameUseCase) {
    return new FavouriteViewModelFactory(gameUseCase);
  }
}
