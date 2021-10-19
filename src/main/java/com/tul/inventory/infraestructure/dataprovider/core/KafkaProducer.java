package com.tul.inventory.infraestructure.dataprovider.core;

import static lombok.AccessLevel.PRIVATE;

import com.tul.inventory.domain.entities.Product;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@FieldDefaults(level = PRIVATE)
public class KafkaProducer {

  final KafkaTemplate<String, Product> processProductTemplate;
  final KafkaTemplate<String, String> deleteProductTemplate;

  @Autowired
  public KafkaProducer(KafkaTemplate<String, Product> processProductTemplate,
      KafkaTemplate<String, String> deleteProductTemplate) {
    this.processProductTemplate = processProductTemplate;
    this.deleteProductTemplate = deleteProductTemplate;
  }

  public void sendDeleteMessage(String message) {
    ListenableFuture<SendResult<String, String>> future = deleteProductTemplate
        .send("delete_product", message);
    future.addCallback(new ListenableFutureCallback<>() {

      @Override
      public void onSuccess(SendResult<String, String> result) {
        System.out.println("Sent message=[" + message +
            "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }

      @Override
      public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=["
            + message + "] due to : " + ex.getMessage());
      }
    });
  }

  public void sendMessage(Product message) {
    ListenableFuture<SendResult<String, Product>> future =
        processProductTemplate.send("process_product", message);

    future.addCallback(new ListenableFutureCallback<>() {

      @Override
      public void onSuccess(SendResult<String, Product> result) {
        System.out.println("Sent message=[" + message +
            "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }

      @Override
      public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=["
            + message + "] due to : " + ex.getMessage());
      }
    });
  }

}
