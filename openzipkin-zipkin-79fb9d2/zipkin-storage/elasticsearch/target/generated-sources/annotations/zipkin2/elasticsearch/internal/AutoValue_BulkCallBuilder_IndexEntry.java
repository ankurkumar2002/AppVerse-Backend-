package zipkin2.elasticsearch.internal;

import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_BulkCallBuilder_IndexEntry<T> extends BulkCallBuilder.IndexEntry<T> {

  private final String index;

  private final String typeName;

  private final T input;

  private final BulkIndexWriter<T> writer;

  AutoValue_BulkCallBuilder_IndexEntry(
      String index,
      String typeName,
      T input,
      BulkIndexWriter<T> writer) {
    if (index == null) {
      throw new NullPointerException("Null index");
    }
    this.index = index;
    if (typeName == null) {
      throw new NullPointerException("Null typeName");
    }
    this.typeName = typeName;
    if (input == null) {
      throw new NullPointerException("Null input");
    }
    this.input = input;
    if (writer == null) {
      throw new NullPointerException("Null writer");
    }
    this.writer = writer;
  }

  @Override
  String index() {
    return index;
  }

  @Override
  String typeName() {
    return typeName;
  }

  @Override
  T input() {
    return input;
  }

  @Override
  BulkIndexWriter<T> writer() {
    return writer;
  }

  @Override
  public String toString() {
    return "IndexEntry{"
        + "index=" + index + ", "
        + "typeName=" + typeName + ", "
        + "input=" + input + ", "
        + "writer=" + writer
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof BulkCallBuilder.IndexEntry) {
      BulkCallBuilder.IndexEntry<?> that = (BulkCallBuilder.IndexEntry<?>) o;
      return this.index.equals(that.index())
          && this.typeName.equals(that.typeName())
          && this.input.equals(that.input())
          && this.writer.equals(that.writer());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= index.hashCode();
    h$ *= 1000003;
    h$ ^= typeName.hashCode();
    h$ *= 1000003;
    h$ ^= input.hashCode();
    h$ *= 1000003;
    h$ ^= writer.hashCode();
    return h$;
  }

}
