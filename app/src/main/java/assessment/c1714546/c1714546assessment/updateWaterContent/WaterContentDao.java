package assessment.c1714546.c1714546assessment.updateWaterContent;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by c1714546 on 5/3/2018.
 */

@Dao
public interface WaterContentDao {

    // Query: reads all records out from DB.
    @Query("SELECT * FROM watercontentrecord")
    List<WaterContentRecord> getAllWaterContentRecords();

    // Insert: places any records given into the DB.
    @Insert
    void insertAll(WaterContentRecord... waterContentRecords);

}
