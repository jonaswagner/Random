package ch.ipt.thread;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedThreadFactory;

public class ConsumerThread extends AbstractGenericThread {

  protected Consumer<Long, String> consumer;

  /*
   * TODO JWA: Specify the correct values somwhere else
   */

  private  final String topic;
  private  final String bootstrapServer;
  private static final String CONSUMER_PREFIX = "Kafka_";

  public ConsumerThread(final String topic, final String bootstrapServer, final String consumerPrefix) {
    this.topic = topic;
    this.bootstrapServer = bootstrapServer;
    this.consumerPrefix = consumerPrefix;
  }

  @Override
  protected void setup() {
    final Properties props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    props.put(
        ConsumerConfig.GROUP_ID_CONFIG,
            consumerPrefix + this.toString()); // e.g. KCONSUMER_ConsumerThread@asdgpoeab
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

    // Create the consumer using props.
    final Consumer<Long, String> consumer = new KafkaConsumer<>(props);

    // Subscribe to the topic.
    consumer.subscribe(Collections.singletonList(topic));
  }

  @Override
  protected boolean checkConfig() {
    return false;
  }

  @Override
  protected void checkInternalState() {}

  @Override
  protected void internalRun() {}

  @Override
  protected void cleanUp() {}

//  void runConsumer() throws InterruptedException {
//
//    final int giveUp = 100;
//    int noRecordsCount = 0;
//
//    while (true) {
//      final ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
//
//      if (consumerRecords.count() == 0) {
//        noRecordsCount++;
//        if (noRecordsCount > giveUp) break;
//        else continue;
//      }
//
//      consumerRecords.forEach(
//          record -> {
//            System.out.printf(
//                "Consumer Record:(%d, %s, %d, %d)\n",
//                record.key(), record.value(), record.partition(), record.offset());
//          });
//
//      consumer.commitAsync();
//    }
//    consumer.close();
//    System.out.println("DONE");
//
//    ManagedThreadFactory factory;
//    factory.newThread();
//  }

}
