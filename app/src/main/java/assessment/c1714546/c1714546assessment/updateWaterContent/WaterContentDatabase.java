package assessment.c1714546.c1714546assessment.updateWaterContent;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Class initialises Database.
 *
 * Created by c1714546 on 5/3/2018.
 *
 * @author Abbey Ross, 04/04/2018.
 * @version 1.0.
 */
@Database(entities = WaterContentRecord.class, version = 8)
public abstract class WaterContentDatabase extends RoomDatabase {
    public abstract WaterContentDao waterContentDao();
}