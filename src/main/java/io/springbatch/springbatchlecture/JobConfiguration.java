package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job job(){
        return jobBuilderFactory.get("mondayJob")
                .start(newStep1())
                .next(newStep2())
                .build();
    }
    @Bean
    public Step newStep2() {
        return stepBuilderFactory.get("mondayStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution,
                                                ChunkContext chunkContext) throws Exception {

                        System.out.println("HELLO SPRING BATCH2");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    } // 의미 없는 주석
    @Bean
    public Step newStep1() {
        return stepBuilderFactory.get("monStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("HELLO SPRING BATCH1");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
