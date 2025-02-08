package zipkin2.elasticsearch;

import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_IndexTemplates extends IndexTemplates {

  private final BaseVersion version;

  private final char indexTypeDelimiter;

  private final String span;

  private final String dependency;

  private final String autocomplete;

  private AutoValue_IndexTemplates(
      BaseVersion version,
      char indexTypeDelimiter,
      String span,
      String dependency,
      String autocomplete) {
    this.version = version;
    this.indexTypeDelimiter = indexTypeDelimiter;
    this.span = span;
    this.dependency = dependency;
    this.autocomplete = autocomplete;
  }

  @Override
  BaseVersion version() {
    return version;
  }

  @Override
  char indexTypeDelimiter() {
    return indexTypeDelimiter;
  }

  @Override
  String span() {
    return span;
  }

  @Override
  String dependency() {
    return dependency;
  }

  @Override
  String autocomplete() {
    return autocomplete;
  }

  @Override
  public String toString() {
    return "IndexTemplates{"
        + "version=" + version + ", "
        + "indexTypeDelimiter=" + indexTypeDelimiter + ", "
        + "span=" + span + ", "
        + "dependency=" + dependency + ", "
        + "autocomplete=" + autocomplete
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof IndexTemplates) {
      IndexTemplates that = (IndexTemplates) o;
      return this.version.equals(that.version())
          && this.indexTypeDelimiter == that.indexTypeDelimiter()
          && this.span.equals(that.span())
          && this.dependency.equals(that.dependency())
          && this.autocomplete.equals(that.autocomplete());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= version.hashCode();
    h$ *= 1000003;
    h$ ^= indexTypeDelimiter;
    h$ *= 1000003;
    h$ ^= span.hashCode();
    h$ *= 1000003;
    h$ ^= dependency.hashCode();
    h$ *= 1000003;
    h$ ^= autocomplete.hashCode();
    return h$;
  }

  static final class Builder implements IndexTemplates.Builder {
    private BaseVersion version;
    private char indexTypeDelimiter;
    private String span;
    private String dependency;
    private String autocomplete;
    private byte set$0;
    Builder() {
    }
    @Override
    public IndexTemplates.Builder version(BaseVersion version) {
      if (version == null) {
        throw new NullPointerException("Null version");
      }
      this.version = version;
      return this;
    }
    @Override
    public IndexTemplates.Builder indexTypeDelimiter(char indexTypeDelimiter) {
      this.indexTypeDelimiter = indexTypeDelimiter;
      set$0 |= (byte) 1;
      return this;
    }
    @Override
    public IndexTemplates.Builder span(String span) {
      if (span == null) {
        throw new NullPointerException("Null span");
      }
      this.span = span;
      return this;
    }
    @Override
    public IndexTemplates.Builder dependency(String dependency) {
      if (dependency == null) {
        throw new NullPointerException("Null dependency");
      }
      this.dependency = dependency;
      return this;
    }
    @Override
    public IndexTemplates.Builder autocomplete(String autocomplete) {
      if (autocomplete == null) {
        throw new NullPointerException("Null autocomplete");
      }
      this.autocomplete = autocomplete;
      return this;
    }
    @Override
    public IndexTemplates build() {
      if (set$0 != 1
          || this.version == null
          || this.span == null
          || this.dependency == null
          || this.autocomplete == null) {
        StringBuilder missing = new StringBuilder();
        if (this.version == null) {
          missing.append(" version");
        }
        if ((set$0 & 1) == 0) {
          missing.append(" indexTypeDelimiter");
        }
        if (this.span == null) {
          missing.append(" span");
        }
        if (this.dependency == null) {
          missing.append(" dependency");
        }
        if (this.autocomplete == null) {
          missing.append(" autocomplete");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_IndexTemplates(
          this.version,
          this.indexTypeDelimiter,
          this.span,
          this.dependency,
          this.autocomplete);
    }
  }

}
