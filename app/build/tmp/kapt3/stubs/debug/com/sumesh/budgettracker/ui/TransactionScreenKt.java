package com.sumesh.budgettracker.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001aj\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00072\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u0014H\u0007\u00a8\u0006\u0015"}, d2 = {"AddTransactionDialog", "", "onDismiss", "Lkotlin/Function0;", "onConfirm", "Lkotlin/Function2;", "", "", "TransactionScreen", "title", "data", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/sumesh/budgettracker/data/Transaction;", "gradient", "Landroidx/compose/ui/graphics/Color;", "modifier", "Landroidx/compose/ui/Modifier;", "onAdd", "onDelete", "Lkotlin/Function1;", "app_debug"})
public final class TransactionScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void TransactionScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.Flow<? extends java.util.List<com.sumesh.budgettracker.data.Transaction>> data, @org.jetbrains.annotations.NotNull()
    java.util.List<androidx.compose.ui.graphics.Color> gradient, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Double, ? super java.lang.String, kotlin.Unit> onAdd, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.sumesh.budgettracker.data.Transaction, kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AddTransactionDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Double, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
}