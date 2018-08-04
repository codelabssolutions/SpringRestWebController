package com.hotfix.util;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.hotfix.jpa.enitity.Product;

@EnableBatchProcessing
@Configuration
public class CsvFileToDatabaseConfig {
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
    
    // begin reader, writer, and processor
    @Bean
    public FlatFileItemReader<Product> csvProductReader(){
        FlatFileItemReader<Product> reader = new FlatFileItemReader<Product>();
        reader.setResource(new ClassPathResource("product.csv"));
        reader.setLineMapper(new DefaultLineMapper<Product>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "id", "name","type", "description", "price", "unit" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {{
                setTargetType(Product.class);
            }});
        }});
        return reader;
    }

	@Bean
	ItemProcessor<Product, Product> csvProductProcessor() {
		return new ProductProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Product> csvProductWriter() {
		 JdbcBatchItemWriter<Product> csvProductWriter = new JdbcBatchItemWriter<Product>();
		 csvProductWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
		 csvProductWriter.setSql("INSERT INTO product (id,name,type, description, price, unit) VALUES (:id,:name,:type, :description, :price, :unit)");
		 csvProductWriter.setDataSource(dataSource);
	     return csvProductWriter;
	}
	// end reader, writer, and processor
	// begin job info
	@Bean
	public Step csvFileToDatabaseStep() {
		return stepBuilderFactory.get("csvFileToDatabaseStep")
				.<Product, Product>chunk(1)
				.reader(csvProductReader())
				.processor(csvProductProcessor())
				.writer(csvProductWriter())
				.build();
	}
	@Bean
	Job csvFileToDatabaseJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("csvFileToDatabaseJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(csvFileToDatabaseStep())
				.end()
				.build();
	}
	 // end job info
}
