package zipkin2.elasticsearch.internal;

import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_IndexNameFormatter extends IndexNameFormatter {

  private final String index;

  private final char dateSeparator;

  private final DateTimeFormatter dateFormat;

  private AutoValue_IndexNameFormatter(
      String index,
      char dateSeparator,
      DateTimeFormatter dateFormat) {
    this.index = index;
    this.dateSeparator = dateSeparator;
    this.dateFormat = dateFormat;
  }

  @Override
  public String index() {
    return index;
  }

  @Override
  char dateSeparator() {
    return dateSeparator;
  }

  @Override
  DateTimeFormatter dateFormat() {
    return dateFormat;
  }

  @Override
  public String toString() {
    return "IndexNameFormatter{"
        + "index=" + index + ", "
        + "dateSeparator=" + dateSeparator + ", "
        + "dateFormat=" + dateFormat
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof IndexNameFormatter) {
      IndexNameFormatter that = (IndexNameFormatter) o;
      return this.index.equals(that.index())
          && this.dateSeparator == that.dateSeparator()
          && this.dateFormat.equals(that.dateFormat());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= index.hashCode();
    h$ *= 1000003;
    h$ ^= dateSeparator;
    h$ *= 1000003;
    h$ ^= dateFormat.hashCode();
    return h$;
  }

  @Override
  public IndexNameFormatter.Builder toBuilder() {
    return new AutoValue_IndexNameFormatter.Builder(this);
  }

  static final class Builder extends IndexNameFormatter.Builder {
    private String index;
    private char dateSeparator;
    private DateTimeFormatter dateFormat;
    private byte set$0;
    Builder() {
    }
    Builder(IndexNameFormatter source) {
      this.index = source.index();
      this.dateSeparator = source.dateSeparator();
      this.dateFormat = source.dateFormat();
      set$0 = (byte) 1;
    }
    @Override
    public IndexNameFormatter.Builder index(String index) {
      if (index == null) {
        throw new NullPointerException("Null index");
      }
      this.index = index;
      return this;
    }
    @Override
    public IndexNameFormatter.Builder dateSeparator(char dateSeparator) {
      this.dateSeparator = dateSeparator;
      set$0 |= (byte) 1;
      return this;
    }
    @Override
    char dateSeparator() {
      if ((set$0 & 1) == 0) {
        throw new IllegalStateException("Property \"dateSeparator\" has not been set");
      }
      return dateSeparator;
    }
    @Override
    IndexNameFormatter.Builder dateFormat(DateTimeFormatter dateFormat) {
      if (dateFormat == null) {
        throw new NullPointerException("Null dateFormat");
      }
      this.dateFormat = dateFormat;
      return this;
    }
    @Override
    IndexNameFormatter autoBuild() {
      if (set$0 != 1
          || this.index == null
          || this.dateFormat == null) {
        StringBuilder missing = new StringBuilder();
        if (this.index == null) {
          missing.append(" index");
        }
        if ((set$0 & 1) == 0) {
          missing.append(" dateSeparator");
        }
        if (this.dateFormat == null) {
          missing.append(" dateFormat");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_IndexNameFormatter(
          this.index,
          this.dateSeparator,
          this.dateFormat);
    }
  }

}
