package com.example.favourite;

import com.example.androidexpertcapstone.ViewModelFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class FavouriteActivity_MembersInjector implements MembersInjector<FavouriteActivity> {
  private final Provider<ViewModelFactory> factoryProvider;

  public FavouriteActivity_MembersInjector(Provider<ViewModelFactory> factoryProvider) {
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<FavouriteActivity> create(
      Provider<ViewModelFactory> factoryProvider) {
    return new FavouriteActivity_MembersInjector(factoryProvider);
  }

  @Override
  public void injectMembers(FavouriteActivity instance) {
    injectFactory(instance, factoryProvider.get());
  }

  @InjectedFieldSignature("com.example.favourite.FavouriteActivity.factory")
  public static void injectFactory(FavouriteActivity instance, ViewModelFactory factory) {
    instance.factory = factory;
  }
}
