package com.sumesh.budgettracker.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Lcom/sumesh/budgettracker/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "categoryDao", "Lcom/sumesh/budgettracker/data/CategoryDao;", "transactionDao", "Lcom/sumesh/budgettracker/data/TransactionDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.sumesh.budgettracker.data.Transaction.class, com.sumesh.budgettracker.data.Category.class}, version = 2, exportSchema = false)
@androidx.room.TypeConverters(value = {com.sumesh.budgettracker.data.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.sumesh.budgettracker.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.sumesh.budgettracker.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.sumesh.budgettracker.data.TransactionDao transactionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.sumesh.budgettracker.data.CategoryDao categoryDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/sumesh/budgettracker/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/sumesh/budgettracker/data/AppDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.sumesh.budgettracker.data.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}