package com.sumesh.budgettracker;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007J-\u0010&\u001a\u00020\"2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\u00072\b\u0010+\u001a\u0004\u0018\u00010,\u00a2\u0006\u0002\u0010-J\u000e\u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u00020\u0011J\u000e\u00100\u001a\u00020\"2\u0006\u00101\u001a\u00020\rJ\u000e\u00102\u001a\u00020\"H\u0082@\u00a2\u0006\u0002\u00103J\u000e\u00104\u001a\u00020\"2\u0006\u00105\u001a\u00020\tJ\u000e\u00106\u001a\u00020\"2\u0006\u0010/\u001a\u00020\u0011J\u000e\u00107\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lcom/sumesh/budgettracker/TransactionViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_fullName", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_monthlyBudget", "", "allTransactions", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/sumesh/budgettracker/data/Transaction;", "getAllTransactions", "()Lkotlinx/coroutines/flow/Flow;", "categories", "Lcom/sumesh/budgettracker/data/Category;", "getCategories", "categoryDao", "Lcom/sumesh/budgettracker/data/CategoryDao;", "db", "Lcom/sumesh/budgettracker/data/AppDatabase;", "fullName", "Lkotlinx/coroutines/flow/StateFlow;", "getFullName", "()Lkotlinx/coroutines/flow/StateFlow;", "monthlyBudget", "getMonthlyBudget", "prefs", "Lcom/sumesh/budgettracker/PreferencesManager;", "transactionDao", "Lcom/sumesh/budgettracker/data/TransactionDao;", "addCategory", "", "name", "icon", "colorHex", "addTransaction", "type", "Lcom/sumesh/budgettracker/data/TransactionType;", "amount", "note", "categoryId", "", "(Lcom/sumesh/budgettracker/data/TransactionType;DLjava/lang/String;Ljava/lang/Integer;)V", "deleteCategory", "category", "deleteTransaction", "transaction", "seedDefaultCategoriesIfNeeded", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBudget", "budget", "updateCategory", "updateName", "app_debug"})
public final class TransactionViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.sumesh.budgettracker.data.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sumesh.budgettracker.data.TransactionDao transactionDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sumesh.budgettracker.data.CategoryDao categoryDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sumesh.budgettracker.PreferencesManager prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.sumesh.budgettracker.data.Transaction>> allTransactions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.sumesh.budgettracker.data.Category>> categories = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _fullName = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> fullName = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Double> _monthlyBudget = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> monthlyBudget = null;
    
    public TransactionViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.sumesh.budgettracker.data.Transaction>> getAllTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.sumesh.budgettracker.data.Category>> getCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getFullName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getMonthlyBudget() {
        return null;
    }
    
    private final java.lang.Object seedDefaultCategoriesIfNeeded(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void addTransaction(@org.jetbrains.annotations.NotNull()
    com.sumesh.budgettracker.data.TransactionType type, double amount, @org.jetbrains.annotations.NotNull()
    java.lang.String note, @org.jetbrains.annotations.Nullable()
    java.lang.Integer categoryId) {
    }
    
    public final void deleteTransaction(@org.jetbrains.annotations.NotNull()
    com.sumesh.budgettracker.data.Transaction transaction) {
    }
    
    public final void addCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String icon, @org.jetbrains.annotations.NotNull()
    java.lang.String colorHex) {
    }
    
    public final void updateCategory(@org.jetbrains.annotations.NotNull()
    com.sumesh.budgettracker.data.Category category) {
    }
    
    public final void deleteCategory(@org.jetbrains.annotations.NotNull()
    com.sumesh.budgettracker.data.Category category) {
    }
    
    public final void updateName(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void updateBudget(double budget) {
    }
}