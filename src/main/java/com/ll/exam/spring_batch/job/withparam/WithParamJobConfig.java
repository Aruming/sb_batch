package com.ll.exam.spring_batch.job.withparam;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WithParamJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job withParamJob() {
        return jobBuilderFactory.get("withParamJob")
                .start(withParamStep1())
                .build();
    }

    @JobScope
    @Bean
    public Step withParamStep1() {
        return stepBuilderFactory.get("withParamStep1")
                .tasklet(withParamStep1Tasklet1())
                .build();
    }

    @StepScope
    @Bean
    public Tasklet withParamStep1Tasklet1() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("withParam Tacklet 1");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
