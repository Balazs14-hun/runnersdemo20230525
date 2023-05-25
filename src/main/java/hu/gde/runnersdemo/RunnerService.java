package hu.gde.runnersdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;

    @Autowired
    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public double getAverageLaptime(Long runnerId) {
        RunnerEntity runner = runnerRepository.findById(runnerId).orElse(null);
        if (runner != null) {
            List<LapTimeEntity> laptimes = runner.getLaptimes();
            int totalTime = 0;
            for (LapTimeEntity laptime : laptimes) {
                totalTime += laptime.getTimeSeconds();
            }
            return (double) totalTime / laptimes.size();
        } else {
            return -1.0;
        }
    }

    public List<RunnerEntity> getAllRunners() {
        return runnerRepository.findAll();
    }

    public RunnerEntity getTallestRunner(){
        List<RunnerEntity> allRunners = getAllRunners();
        RunnerEntity tallestRunner = allRunners.get(0);
        boolean isFirstElement = true;

        for (RunnerEntity runner : allRunners) {
            if(isFirstElement){
                isFirstElement = false;
                continue;
            }
            if(runner.getHeight() > tallestRunner.getHeight()){
                tallestRunner = runner;
            }
        }
        return tallestRunner;
    }
}
