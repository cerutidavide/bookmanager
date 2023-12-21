package com.ceruti.bookmanager.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {
	private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);
	@Bean
	@ConfigurationProperties
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(DataSourceBuilder.create().url("jdbc:h2:tcp://localhost:9092/~/TestDUE").username("SA").password("sa").build());
	}

	@Bean(name = "applicationJdbcTemplate")
	public JdbcTemplate applicationDataConnection(){
		return new JdbcTemplate(dataSourceTransactionManager().getDataSource());
	}
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSourceTransactionManager().getDataSource());
	}// tag::readerwriterprocessor[]
	@Bean
	public FlatFileItemReader<Person> reader() {

		return new FlatFileItemReaderBuilder<Person>()
			.name("personItemReader")
			.resource(new ClassPathResource("sample-data.csv"))
			.delimited()
			.names("firstName", "lastName")
			.targetType(Person.class)
			.build();
	}

	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {

		return new JdbcBatchItemWriterBuilder<Person>()
			.sql("INSERT INTO PEOPLE (first_name, last_name) VALUES (:firstName, :lastName)")
			.dataSource(dataSourceTransactionManager().getDataSource())
			.beanMapped()
			.build();
	}
	// end::readerwriterprocessor[]
	// tag::jobstep[]
//	@Bean
//	public Job importUserJob(JobRepository jobRepository,Step step1, JobCompletionNotificationListener listener) {
//		return new JobBuilder("importUserJob", jobRepository)
//			.listener(listener)
//			.start(step1)
//			.build();
//	}
//
//	@Bean
//	public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
//					  FlatFileItemReader<Person> reader, PersonItemProcessor processor, JdbcBatchItemWriter<Person> writer) {
//		return new StepBuilder("step1", jobRepository)
//			.<Person, Person> chunk(3, transactionManager)
//			.reader(reader)
//			.processor(processor)
//			.writer(writer)
//			.build();
//	}
	// end::jobstep[]
}
