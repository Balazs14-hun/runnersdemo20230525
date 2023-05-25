package hu.gde.runnersdemo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RunnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long runnerId;
    private String runnerName;
    private long averagePace;
    private int height;

    @OneToMany(mappedBy = "runner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LapTimeEntity> laptimes = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private SponsorEntity sponsor;

    public RunnerEntity() {
    }

    public long getRunnerId() {
        return runnerId;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public long getAveragePace() {
        return averagePace;
    }

    public int getHeight() {
        return height;
    }

    public void setRunnerId(long runnerId) {
        this.runnerId = runnerId;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public void setAveragePace(long averagePace) {
        this.averagePace = averagePace;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<LapTimeEntity> getLaptimes() {
        return laptimes;
    }

    public void setSponsor(SponsorEntity sponsor) {
        this.sponsor = sponsor;
    }

    public SponsorEntity getSponsor() {
        return sponsor;
    }

    public long getSponsorId(){
        long sponsorId = getSponsor().getSponsorId();
        return sponsorId;
    }
}
