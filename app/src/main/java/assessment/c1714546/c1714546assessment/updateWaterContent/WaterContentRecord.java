package assessment.c1714546.c1714546assessment.updateWaterContent;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by c1714546 on 5/3/2018.
 */

@Entity
public class WaterContentRecord {
    public static int numOfRecs;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="number_of_glasses")
    private int numberOfGlasses;

    @ColumnInfo(name="time_of_consumption")
    private String timeOfConsumption;

    public WaterContentRecord(int numberOfGlasses, String timeOfConsumption) {
        this.numberOfGlasses = numberOfGlasses;
        this.timeOfConsumption = timeOfConsumption;
        numOfRecs++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfGlasses() {
        return numberOfGlasses;
    }

    public void setNumberOfGlasses(int numberOfGlasses) {
        this.numberOfGlasses = numberOfGlasses;
    }

    public String getTimeOfConsumption() {
        return timeOfConsumption;
    }

    public void setTimeOfConsumption(String timeOfConsumption) {
        this.timeOfConsumption = timeOfConsumption;
    }

}
