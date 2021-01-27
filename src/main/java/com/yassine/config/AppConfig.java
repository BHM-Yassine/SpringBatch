package com.yassine.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.yassine.entities.TransactionHolder;

import com.yassine.entities.Transaction;;

@Configuration
@EnableBatchProcessing // activer spring batch
public class AppConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private ItemReader<TransactionHolder> transactionItemReader;
	@Autowired
	private ItemProcessor<TransactionHolder, Transaction> transactionItemProcessor;
	@Autowired
	private ItemWriter<Transaction> transactionItemWriter;

//	@Value("classpath:/data.csv")
//	private Resource inputResource;

	@Bean
	public Job job() {
		return jobBuilderFactory.get("transactionJob").start(step1()).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<TransactionHolder, Transaction>chunk(3)
				.reader(transactionItemReader)
				.processor(transactionItemProcessor)
				.writer(transactionItemWriter)
				.build();
	}
	
//	@Bean 
//	TransactionWriter transactionWriter() {
//		return new TransactionWriter();
//	}
//	
//	@Bean 
//	TransactionProcess transactionProcess() {
//		return new TransactionProcess();
//	}
	


	@Bean
	public FlatFileItemReader<TransactionHolder> reader(@Value("classpath:/data.csv") Resource inputResource) {		
		System.out.println("Reader Runned");
		FlatFileItemReader<TransactionHolder> itemReader = new FlatFileItemReader<TransactionHolder>();
		// itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		itemReader.setResource(inputResource);
		System.out.println("Reader Closed");
		return itemReader;
	}

	@Bean // lineMapper se charge de traiter une ligne du fichier
	public LineMapper<TransactionHolder> lineMapper() {
		System.out.println("lineMapper Runned");
		DefaultLineMapper<TransactionHolder> lineMapper = new DefaultLineMapper<TransactionHolder>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "idTransaction", "idCompte", "montant", "dateTransaction" });
		lineTokenizer.setDelimiter(",");

		BeanWrapperFieldSetMapper<TransactionHolder> fieldSetMapper = new BeanWrapperFieldSetMapper<TransactionHolder>();
		fieldSetMapper.setTargetType(TransactionHolder.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		System.out.println("lineMapper Closed");
		return lineMapper;
	}

}
