package com.example.favourite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0003R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/favourite/FavouriteViewModel;", "Landroidx/lifecycle/ViewModel;", "gameUseCase", "Lcom/example/core/domain/usecase/GameUseCase;", "(Lcom/example/core/domain/usecase/GameUseCase;)V", "_favouriteGames", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/core/domain/model/Game;", "favouriteGames", "Landroidx/lifecycle/LiveData;", "getFavouriteGames", "()Landroidx/lifecycle/LiveData;", "loadFavouriteGames", "", "favourite_debug"})
public final class FavouriteViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.core.domain.usecase.GameUseCase gameUseCase = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.core.domain.model.Game>> _favouriteGames = null;
    
    @javax.inject.Inject
    public FavouriteViewModel(@org.jetbrains.annotations.NotNull
    com.example.core.domain.usecase.GameUseCase gameUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.core.domain.model.Game>> getFavouriteGames() {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"CheckResult"})
    private final void loadFavouriteGames() {
    }
}