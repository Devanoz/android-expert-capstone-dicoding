package com.example.favourite;

import com.example.core.domain.usecase.GameUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class FavouriteViewModel_Factory implements Factory<FavouriteViewModel> {
  private final Provider<GameUseCase> gameUseCaseProvider;

  public FavouriteViewModel_Factory(Provider<GameUseCase> gameUseCaseProvider) {
    this.gameUseCaseProvider = gameUseCaseProvider;
  }

  @Override
  public FavouriteViewModel get() {
    return newInstance(gameUseCaseProvider.get());
  }

  public static FavouriteViewModel_Factory create(Provider<GameUseCase> gameUseCaseProvider) {
    return new FavouriteViewModel_Factory(gameUseCaseProvider);
  }

  public static FavouriteViewModel newInstance(GameUseCase gameUseCase) {
    return new FavouriteViewModel(gameUseCase);
  }
}
