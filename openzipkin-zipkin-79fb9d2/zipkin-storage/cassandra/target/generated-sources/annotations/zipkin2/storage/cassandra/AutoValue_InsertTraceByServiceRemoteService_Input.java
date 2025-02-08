package zipkin2.storage.cassandra;

import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_InsertTraceByServiceRemoteService_Input extends InsertTraceByServiceRemoteService.Input {

  private final String service;

  private final String remote_service;

  private final int bucket;

  private final UUID ts;

  private final String trace_id;

  AutoValue_InsertTraceByServiceRemoteService_Input(
      String service,
      String remote_service,
      int bucket,
      UUID ts,
      String trace_id) {
    if (service == null) {
      throw new NullPointerException("Null service");
    }
    this.service = service;
    if (remote_service == null) {
      throw new NullPointerException("Null remote_service");
    }
    this.remote_service = remote_service;
    this.bucket = bucket;
    if (ts == null) {
      throw new NullPointerException("Null ts");
    }
    this.ts = ts;
    if (trace_id == null) {
      throw new NullPointerException("Null trace_id");
    }
    this.trace_id = trace_id;
  }

  @Override
  String service() {
    return service;
  }

  @Override
  String remote_service() {
    return remote_service;
  }

  @Override
  int bucket() {
    return bucket;
  }

  @Override
  UUID ts() {
    return ts;
  }

  @Override
  String trace_id() {
    return trace_id;
  }

  @Override
  public String toString() {
    return "Input{"
        + "service=" + service + ", "
        + "remote_service=" + remote_service + ", "
        + "bucket=" + bucket + ", "
        + "ts=" + ts + ", "
        + "trace_id=" + trace_id
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof InsertTraceByServiceRemoteService.Input) {
      InsertTraceByServiceRemoteService.Input that = (InsertTraceByServiceRemoteService.Input) o;
      return this.service.equals(that.service())
          && this.remote_service.equals(that.remote_service())
          && this.bucket == that.bucket()
          && this.ts.equals(that.ts())
          && this.trace_id.equals(that.trace_id());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= service.hashCode();
    h$ *= 1000003;
    h$ ^= remote_service.hashCode();
    h$ *= 1000003;
    h$ ^= bucket;
    h$ *= 1000003;
    h$ ^= ts.hashCode();
    h$ *= 1000003;
    h$ ^= trace_id.hashCode();
    return h$;
  }

}
