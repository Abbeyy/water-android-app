package assessment.c1714546.c1714546assessment.updateWaterContent;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by c1714546 on 5/3/2018.
 */

@Dao
public interface WaterContentDao {

    @Query("SELECT * FROM watercontentrecord")
    List<WaterContentRecord> getAllWaterContentRecords();

    @Query("SELECT * FROM watercontentrecord WHERE time_of_consumption = :time")
    List<WaterContentRecord> getWaterContentRecordsByTime(String time);

    @Insert
    void insertAll(WaterContentRecord... waterContentRecords);

}
