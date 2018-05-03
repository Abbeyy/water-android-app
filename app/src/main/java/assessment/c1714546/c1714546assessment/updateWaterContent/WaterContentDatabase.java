package assessment.c1714546.c1714546assessment.updateWaterContent;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by c1714546 on 5/3/2018.
 */

@Database(entities = WaterContentRecord.class, version = 6)
public abstract class WaterContentDatabase extends RoomDatabase {
    public abstract WaterContentDao waterContentDao();
}