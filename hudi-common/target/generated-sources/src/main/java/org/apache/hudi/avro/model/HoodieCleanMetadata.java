/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.apache.hudi.avro.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class HoodieCleanMetadata extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -8274813426307958894L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCleanMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"startCleanTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"timeTakenInMillis\",\"type\":\"long\"},{\"name\":\"totalFilesDeleted\",\"type\":\"int\"},{\"name\":\"earliestCommitToRetain\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"partitionMetadata\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"HoodieCleanPartitionMetadata\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"policy\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"deletePathPatterns\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"successDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"failedDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"isPartitionDeleted\",\"type\":[\"null\",\"boolean\"],\"default\":null}]},\"avro.java.string\":\"String\"}},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"bootstrapPartitionMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"HoodieCleanPartitionMetadata\",\"avro.java.string\":\"String\",\"default\":null}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<HoodieCleanMetadata> ENCODER =
      new BinaryMessageEncoder<HoodieCleanMetadata>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<HoodieCleanMetadata> DECODER =
      new BinaryMessageDecoder<HoodieCleanMetadata>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<HoodieCleanMetadata> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<HoodieCleanMetadata> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<HoodieCleanMetadata>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this HoodieCleanMetadata to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a HoodieCleanMetadata from a ByteBuffer. */
  public static HoodieCleanMetadata fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String startCleanTime;
  @Deprecated public long timeTakenInMillis;
  @Deprecated public int totalFilesDeleted;
  @Deprecated public java.lang.String earliestCommitToRetain;
  @Deprecated public java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> partitionMetadata;
  @Deprecated public java.lang.Integer version;
  @Deprecated public java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> bootstrapPartitionMetadata;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public HoodieCleanMetadata() {}

  /**
   * All-args constructor.
   * @param startCleanTime The new value for startCleanTime
   * @param timeTakenInMillis The new value for timeTakenInMillis
   * @param totalFilesDeleted The new value for totalFilesDeleted
   * @param earliestCommitToRetain The new value for earliestCommitToRetain
   * @param partitionMetadata The new value for partitionMetadata
   * @param version The new value for version
   * @param bootstrapPartitionMetadata The new value for bootstrapPartitionMetadata
   */
  public HoodieCleanMetadata(java.lang.String startCleanTime, java.lang.Long timeTakenInMillis, java.lang.Integer totalFilesDeleted, java.lang.String earliestCommitToRetain, java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> partitionMetadata, java.lang.Integer version, java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> bootstrapPartitionMetadata) {
    this.startCleanTime = startCleanTime;
    this.timeTakenInMillis = timeTakenInMillis;
    this.totalFilesDeleted = totalFilesDeleted;
    this.earliestCommitToRetain = earliestCommitToRetain;
    this.partitionMetadata = partitionMetadata;
    this.version = version;
    this.bootstrapPartitionMetadata = bootstrapPartitionMetadata;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return startCleanTime;
    case 1: return timeTakenInMillis;
    case 2: return totalFilesDeleted;
    case 3: return earliestCommitToRetain;
    case 4: return partitionMetadata;
    case 5: return version;
    case 6: return bootstrapPartitionMetadata;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: startCleanTime = (java.lang.String)value$; break;
    case 1: timeTakenInMillis = (java.lang.Long)value$; break;
    case 2: totalFilesDeleted = (java.lang.Integer)value$; break;
    case 3: earliestCommitToRetain = (java.lang.String)value$; break;
    case 4: partitionMetadata = (java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata>)value$; break;
    case 5: version = (java.lang.Integer)value$; break;
    case 6: bootstrapPartitionMetadata = (java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'startCleanTime' field.
   * @return The value of the 'startCleanTime' field.
   */
  public java.lang.String getStartCleanTime() {
    return startCleanTime;
  }

  /**
   * Sets the value of the 'startCleanTime' field.
   * @param value the value to set.
   */
  public void setStartCleanTime(java.lang.String value) {
    this.startCleanTime = value;
  }

  /**
   * Gets the value of the 'timeTakenInMillis' field.
   * @return The value of the 'timeTakenInMillis' field.
   */
  public java.lang.Long getTimeTakenInMillis() {
    return timeTakenInMillis;
  }

  /**
   * Sets the value of the 'timeTakenInMillis' field.
   * @param value the value to set.
   */
  public void setTimeTakenInMillis(java.lang.Long value) {
    this.timeTakenInMillis = value;
  }

  /**
   * Gets the value of the 'totalFilesDeleted' field.
   * @return The value of the 'totalFilesDeleted' field.
   */
  public java.lang.Integer getTotalFilesDeleted() {
    return totalFilesDeleted;
  }

  /**
   * Sets the value of the 'totalFilesDeleted' field.
   * @param value the value to set.
   */
  public void setTotalFilesDeleted(java.lang.Integer value) {
    this.totalFilesDeleted = value;
  }

  /**
   * Gets the value of the 'earliestCommitToRetain' field.
   * @return The value of the 'earliestCommitToRetain' field.
   */
  public java.lang.String getEarliestCommitToRetain() {
    return earliestCommitToRetain;
  }

  /**
   * Sets the value of the 'earliestCommitToRetain' field.
   * @param value the value to set.
   */
  public void setEarliestCommitToRetain(java.lang.String value) {
    this.earliestCommitToRetain = value;
  }

  /**
   * Gets the value of the 'partitionMetadata' field.
   * @return The value of the 'partitionMetadata' field.
   */
  public java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> getPartitionMetadata() {
    return partitionMetadata;
  }

  /**
   * Sets the value of the 'partitionMetadata' field.
   * @param value the value to set.
   */
  public void setPartitionMetadata(java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> value) {
    this.partitionMetadata = value;
  }

  /**
   * Gets the value of the 'version' field.
   * @return The value of the 'version' field.
   */
  public java.lang.Integer getVersion() {
    return version;
  }

  /**
   * Sets the value of the 'version' field.
   * @param value the value to set.
   */
  public void setVersion(java.lang.Integer value) {
    this.version = value;
  }

  /**
   * Gets the value of the 'bootstrapPartitionMetadata' field.
   * @return The value of the 'bootstrapPartitionMetadata' field.
   */
  public java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> getBootstrapPartitionMetadata() {
    return bootstrapPartitionMetadata;
  }

  /**
   * Sets the value of the 'bootstrapPartitionMetadata' field.
   * @param value the value to set.
   */
  public void setBootstrapPartitionMetadata(java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> value) {
    this.bootstrapPartitionMetadata = value;
  }

  /**
   * Creates a new HoodieCleanMetadata RecordBuilder.
   * @return A new HoodieCleanMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCleanMetadata.Builder newBuilder() {
    return new org.apache.hudi.avro.model.HoodieCleanMetadata.Builder();
  }

  /**
   * Creates a new HoodieCleanMetadata RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new HoodieCleanMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCleanMetadata.Builder newBuilder(org.apache.hudi.avro.model.HoodieCleanMetadata.Builder other) {
    return new org.apache.hudi.avro.model.HoodieCleanMetadata.Builder(other);
  }

  /**
   * Creates a new HoodieCleanMetadata RecordBuilder by copying an existing HoodieCleanMetadata instance.
   * @param other The existing instance to copy.
   * @return A new HoodieCleanMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCleanMetadata.Builder newBuilder(org.apache.hudi.avro.model.HoodieCleanMetadata other) {
    return new org.apache.hudi.avro.model.HoodieCleanMetadata.Builder(other);
  }

  /**
   * RecordBuilder for HoodieCleanMetadata instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<HoodieCleanMetadata>
    implements org.apache.avro.data.RecordBuilder<HoodieCleanMetadata> {

    private java.lang.String startCleanTime;
    private long timeTakenInMillis;
    private int totalFilesDeleted;
    private java.lang.String earliestCommitToRetain;
    private java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> partitionMetadata;
    private java.lang.Integer version;
    private java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> bootstrapPartitionMetadata;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieCleanMetadata.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.startCleanTime)) {
        this.startCleanTime = data().deepCopy(fields()[0].schema(), other.startCleanTime);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timeTakenInMillis)) {
        this.timeTakenInMillis = data().deepCopy(fields()[1].schema(), other.timeTakenInMillis);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.totalFilesDeleted)) {
        this.totalFilesDeleted = data().deepCopy(fields()[2].schema(), other.totalFilesDeleted);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.earliestCommitToRetain)) {
        this.earliestCommitToRetain = data().deepCopy(fields()[3].schema(), other.earliestCommitToRetain);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.partitionMetadata)) {
        this.partitionMetadata = data().deepCopy(fields()[4].schema(), other.partitionMetadata);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.version)) {
        this.version = data().deepCopy(fields()[5].schema(), other.version);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.bootstrapPartitionMetadata)) {
        this.bootstrapPartitionMetadata = data().deepCopy(fields()[6].schema(), other.bootstrapPartitionMetadata);
        fieldSetFlags()[6] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing HoodieCleanMetadata instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieCleanMetadata other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.startCleanTime)) {
        this.startCleanTime = data().deepCopy(fields()[0].schema(), other.startCleanTime);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timeTakenInMillis)) {
        this.timeTakenInMillis = data().deepCopy(fields()[1].schema(), other.timeTakenInMillis);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.totalFilesDeleted)) {
        this.totalFilesDeleted = data().deepCopy(fields()[2].schema(), other.totalFilesDeleted);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.earliestCommitToRetain)) {
        this.earliestCommitToRetain = data().deepCopy(fields()[3].schema(), other.earliestCommitToRetain);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.partitionMetadata)) {
        this.partitionMetadata = data().deepCopy(fields()[4].schema(), other.partitionMetadata);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.version)) {
        this.version = data().deepCopy(fields()[5].schema(), other.version);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.bootstrapPartitionMetadata)) {
        this.bootstrapPartitionMetadata = data().deepCopy(fields()[6].schema(), other.bootstrapPartitionMetadata);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'startCleanTime' field.
      * @return The value.
      */
    public java.lang.String getStartCleanTime() {
      return startCleanTime;
    }

    /**
      * Sets the value of the 'startCleanTime' field.
      * @param value The value of 'startCleanTime'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder setStartCleanTime(java.lang.String value) {
      validate(fields()[0], value);
      this.startCleanTime = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'startCleanTime' field has been set.
      * @return True if the 'startCleanTime' field has been set, false otherwise.
      */
    public boolean hasStartCleanTime() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'startCleanTime' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder clearStartCleanTime() {
      startCleanTime = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'timeTakenInMillis' field.
      * @return The value.
      */
    public java.lang.Long getTimeTakenInMillis() {
      return timeTakenInMillis;
    }

    /**
      * Sets the value of the 'timeTakenInMillis' field.
      * @param value The value of 'timeTakenInMillis'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder setTimeTakenInMillis(long value) {
      validate(fields()[1], value);
      this.timeTakenInMillis = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'timeTakenInMillis' field has been set.
      * @return True if the 'timeTakenInMillis' field has been set, false otherwise.
      */
    public boolean hasTimeTakenInMillis() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'timeTakenInMillis' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder clearTimeTakenInMillis() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'totalFilesDeleted' field.
      * @return The value.
      */
    public java.lang.Integer getTotalFilesDeleted() {
      return totalFilesDeleted;
    }

    /**
      * Sets the value of the 'totalFilesDeleted' field.
      * @param value The value of 'totalFilesDeleted'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder setTotalFilesDeleted(int value) {
      validate(fields()[2], value);
      this.totalFilesDeleted = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'totalFilesDeleted' field has been set.
      * @return True if the 'totalFilesDeleted' field has been set, false otherwise.
      */
    public boolean hasTotalFilesDeleted() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'totalFilesDeleted' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder clearTotalFilesDeleted() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'earliestCommitToRetain' field.
      * @return The value.
      */
    public java.lang.String getEarliestCommitToRetain() {
      return earliestCommitToRetain;
    }

    /**
      * Sets the value of the 'earliestCommitToRetain' field.
      * @param value The value of 'earliestCommitToRetain'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder setEarliestCommitToRetain(java.lang.String value) {
      validate(fields()[3], value);
      this.earliestCommitToRetain = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'earliestCommitToRetain' field has been set.
      * @return True if the 'earliestCommitToRetain' field has been set, false otherwise.
      */
    public boolean hasEarliestCommitToRetain() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'earliestCommitToRetain' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder clearEarliestCommitToRetain() {
      earliestCommitToRetain = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'partitionMetadata' field.
      * @return The value.
      */
    public java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> getPartitionMetadata() {
      return partitionMetadata;
    }

    /**
      * Sets the value of the 'partitionMetadata' field.
      * @param value The value of 'partitionMetadata'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder setPartitionMetadata(java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> value) {
      validate(fields()[4], value);
      this.partitionMetadata = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'partitionMetadata' field has been set.
      * @return True if the 'partitionMetadata' field has been set, false otherwise.
      */
    public boolean hasPartitionMetadata() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'partitionMetadata' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder clearPartitionMetadata() {
      partitionMetadata = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'version' field.
      * @return The value.
      */
    public java.lang.Integer getVersion() {
      return version;
    }

    /**
      * Sets the value of the 'version' field.
      * @param value The value of 'version'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder setVersion(java.lang.Integer value) {
      validate(fields()[5], value);
      this.version = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'version' field has been set.
      * @return True if the 'version' field has been set, false otherwise.
      */
    public boolean hasVersion() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'version' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder clearVersion() {
      version = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'bootstrapPartitionMetadata' field.
      * @return The value.
      */
    public java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> getBootstrapPartitionMetadata() {
      return bootstrapPartitionMetadata;
    }

    /**
      * Sets the value of the 'bootstrapPartitionMetadata' field.
      * @param value The value of 'bootstrapPartitionMetadata'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder setBootstrapPartitionMetadata(java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata> value) {
      validate(fields()[6], value);
      this.bootstrapPartitionMetadata = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'bootstrapPartitionMetadata' field has been set.
      * @return True if the 'bootstrapPartitionMetadata' field has been set, false otherwise.
      */
    public boolean hasBootstrapPartitionMetadata() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'bootstrapPartitionMetadata' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCleanMetadata.Builder clearBootstrapPartitionMetadata() {
      bootstrapPartitionMetadata = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HoodieCleanMetadata build() {
      try {
        HoodieCleanMetadata record = new HoodieCleanMetadata();
        record.startCleanTime = fieldSetFlags()[0] ? this.startCleanTime : (java.lang.String) defaultValue(fields()[0]);
        record.timeTakenInMillis = fieldSetFlags()[1] ? this.timeTakenInMillis : (java.lang.Long) defaultValue(fields()[1]);
        record.totalFilesDeleted = fieldSetFlags()[2] ? this.totalFilesDeleted : (java.lang.Integer) defaultValue(fields()[2]);
        record.earliestCommitToRetain = fieldSetFlags()[3] ? this.earliestCommitToRetain : (java.lang.String) defaultValue(fields()[3]);
        record.partitionMetadata = fieldSetFlags()[4] ? this.partitionMetadata : (java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata>) defaultValue(fields()[4]);
        record.version = fieldSetFlags()[5] ? this.version : (java.lang.Integer) defaultValue(fields()[5]);
        record.bootstrapPartitionMetadata = fieldSetFlags()[6] ? this.bootstrapPartitionMetadata : (java.util.Map<java.lang.String,org.apache.hudi.avro.model.HoodieCleanPartitionMetadata>) defaultValue(fields()[6]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<HoodieCleanMetadata>
    WRITER$ = (org.apache.avro.io.DatumWriter<HoodieCleanMetadata>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<HoodieCleanMetadata>
    READER$ = (org.apache.avro.io.DatumReader<HoodieCleanMetadata>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
