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
public class HoodieCompactionWriteStat extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8927392485045241479L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCompactionWriteStat\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"partitionPath\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"hoodieWriteStat\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieWriteStat\",\"fields\":[{\"name\":\"fileId\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"path\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"prevCommit\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"numWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numDeletes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numUpdateWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteBytes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteErrors\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numInserts\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalCorruptLogBlock\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalRollbackBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"fileSizeInBytes\",\"type\":[\"null\",\"long\"],\"default\":null}]}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<HoodieCompactionWriteStat> ENCODER =
      new BinaryMessageEncoder<HoodieCompactionWriteStat>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<HoodieCompactionWriteStat> DECODER =
      new BinaryMessageDecoder<HoodieCompactionWriteStat>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<HoodieCompactionWriteStat> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<HoodieCompactionWriteStat> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<HoodieCompactionWriteStat>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this HoodieCompactionWriteStat to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a HoodieCompactionWriteStat from a ByteBuffer. */
  public static HoodieCompactionWriteStat fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence partitionPath;
  @Deprecated public java.lang.Long totalLogRecords;
  @Deprecated public java.lang.Long totalLogFiles;
  @Deprecated public java.lang.Long totalUpdatedRecordsCompacted;
  @Deprecated public org.apache.hudi.avro.model.HoodieWriteStat hoodieWriteStat;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public HoodieCompactionWriteStat() {}

  /**
   * All-args constructor.
   * @param partitionPath The new value for partitionPath
   * @param totalLogRecords The new value for totalLogRecords
   * @param totalLogFiles The new value for totalLogFiles
   * @param totalUpdatedRecordsCompacted The new value for totalUpdatedRecordsCompacted
   * @param hoodieWriteStat The new value for hoodieWriteStat
   */
  public HoodieCompactionWriteStat(java.lang.CharSequence partitionPath, java.lang.Long totalLogRecords, java.lang.Long totalLogFiles, java.lang.Long totalUpdatedRecordsCompacted, org.apache.hudi.avro.model.HoodieWriteStat hoodieWriteStat) {
    this.partitionPath = partitionPath;
    this.totalLogRecords = totalLogRecords;
    this.totalLogFiles = totalLogFiles;
    this.totalUpdatedRecordsCompacted = totalUpdatedRecordsCompacted;
    this.hoodieWriteStat = hoodieWriteStat;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return partitionPath;
    case 1: return totalLogRecords;
    case 2: return totalLogFiles;
    case 3: return totalUpdatedRecordsCompacted;
    case 4: return hoodieWriteStat;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: partitionPath = (java.lang.CharSequence)value$; break;
    case 1: totalLogRecords = (java.lang.Long)value$; break;
    case 2: totalLogFiles = (java.lang.Long)value$; break;
    case 3: totalUpdatedRecordsCompacted = (java.lang.Long)value$; break;
    case 4: hoodieWriteStat = (org.apache.hudi.avro.model.HoodieWriteStat)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'partitionPath' field.
   * @return The value of the 'partitionPath' field.
   */
  public java.lang.CharSequence getPartitionPath() {
    return partitionPath;
  }

  /**
   * Sets the value of the 'partitionPath' field.
   * @param value the value to set.
   */
  public void setPartitionPath(java.lang.CharSequence value) {
    this.partitionPath = value;
  }

  /**
   * Gets the value of the 'totalLogRecords' field.
   * @return The value of the 'totalLogRecords' field.
   */
  public java.lang.Long getTotalLogRecords() {
    return totalLogRecords;
  }

  /**
   * Sets the value of the 'totalLogRecords' field.
   * @param value the value to set.
   */
  public void setTotalLogRecords(java.lang.Long value) {
    this.totalLogRecords = value;
  }

  /**
   * Gets the value of the 'totalLogFiles' field.
   * @return The value of the 'totalLogFiles' field.
   */
  public java.lang.Long getTotalLogFiles() {
    return totalLogFiles;
  }

  /**
   * Sets the value of the 'totalLogFiles' field.
   * @param value the value to set.
   */
  public void setTotalLogFiles(java.lang.Long value) {
    this.totalLogFiles = value;
  }

  /**
   * Gets the value of the 'totalUpdatedRecordsCompacted' field.
   * @return The value of the 'totalUpdatedRecordsCompacted' field.
   */
  public java.lang.Long getTotalUpdatedRecordsCompacted() {
    return totalUpdatedRecordsCompacted;
  }

  /**
   * Sets the value of the 'totalUpdatedRecordsCompacted' field.
   * @param value the value to set.
   */
  public void setTotalUpdatedRecordsCompacted(java.lang.Long value) {
    this.totalUpdatedRecordsCompacted = value;
  }

  /**
   * Gets the value of the 'hoodieWriteStat' field.
   * @return The value of the 'hoodieWriteStat' field.
   */
  public org.apache.hudi.avro.model.HoodieWriteStat getHoodieWriteStat() {
    return hoodieWriteStat;
  }

  /**
   * Sets the value of the 'hoodieWriteStat' field.
   * @param value the value to set.
   */
  public void setHoodieWriteStat(org.apache.hudi.avro.model.HoodieWriteStat value) {
    this.hoodieWriteStat = value;
  }

  /**
   * Creates a new HoodieCompactionWriteStat RecordBuilder.
   * @return A new HoodieCompactionWriteStat RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder newBuilder() {
    return new org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder();
  }

  /**
   * Creates a new HoodieCompactionWriteStat RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new HoodieCompactionWriteStat RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder newBuilder(org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder other) {
    return new org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder(other);
  }

  /**
   * Creates a new HoodieCompactionWriteStat RecordBuilder by copying an existing HoodieCompactionWriteStat instance.
   * @param other The existing instance to copy.
   * @return A new HoodieCompactionWriteStat RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder newBuilder(org.apache.hudi.avro.model.HoodieCompactionWriteStat other) {
    return new org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder(other);
  }

  /**
   * RecordBuilder for HoodieCompactionWriteStat instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<HoodieCompactionWriteStat>
    implements org.apache.avro.data.RecordBuilder<HoodieCompactionWriteStat> {

    private java.lang.CharSequence partitionPath;
    private java.lang.Long totalLogRecords;
    private java.lang.Long totalLogFiles;
    private java.lang.Long totalUpdatedRecordsCompacted;
    private org.apache.hudi.avro.model.HoodieWriteStat hoodieWriteStat;
    private org.apache.hudi.avro.model.HoodieWriteStat.Builder hoodieWriteStatBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.partitionPath)) {
        this.partitionPath = data().deepCopy(fields()[0].schema(), other.partitionPath);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.totalLogRecords)) {
        this.totalLogRecords = data().deepCopy(fields()[1].schema(), other.totalLogRecords);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.totalLogFiles)) {
        this.totalLogFiles = data().deepCopy(fields()[2].schema(), other.totalLogFiles);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.totalUpdatedRecordsCompacted)) {
        this.totalUpdatedRecordsCompacted = data().deepCopy(fields()[3].schema(), other.totalUpdatedRecordsCompacted);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.hoodieWriteStat)) {
        this.hoodieWriteStat = data().deepCopy(fields()[4].schema(), other.hoodieWriteStat);
        fieldSetFlags()[4] = true;
      }
      if (other.hasHoodieWriteStatBuilder()) {
        this.hoodieWriteStatBuilder = org.apache.hudi.avro.model.HoodieWriteStat.newBuilder(other.getHoodieWriteStatBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing HoodieCompactionWriteStat instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieCompactionWriteStat other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.partitionPath)) {
        this.partitionPath = data().deepCopy(fields()[0].schema(), other.partitionPath);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.totalLogRecords)) {
        this.totalLogRecords = data().deepCopy(fields()[1].schema(), other.totalLogRecords);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.totalLogFiles)) {
        this.totalLogFiles = data().deepCopy(fields()[2].schema(), other.totalLogFiles);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.totalUpdatedRecordsCompacted)) {
        this.totalUpdatedRecordsCompacted = data().deepCopy(fields()[3].schema(), other.totalUpdatedRecordsCompacted);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.hoodieWriteStat)) {
        this.hoodieWriteStat = data().deepCopy(fields()[4].schema(), other.hoodieWriteStat);
        fieldSetFlags()[4] = true;
      }
      this.hoodieWriteStatBuilder = null;
    }

    /**
      * Gets the value of the 'partitionPath' field.
      * @return The value.
      */
    public java.lang.CharSequence getPartitionPath() {
      return partitionPath;
    }

    /**
      * Sets the value of the 'partitionPath' field.
      * @param value The value of 'partitionPath'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder setPartitionPath(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.partitionPath = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'partitionPath' field has been set.
      * @return True if the 'partitionPath' field has been set, false otherwise.
      */
    public boolean hasPartitionPath() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'partitionPath' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder clearPartitionPath() {
      partitionPath = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'totalLogRecords' field.
      * @return The value.
      */
    public java.lang.Long getTotalLogRecords() {
      return totalLogRecords;
    }

    /**
      * Sets the value of the 'totalLogRecords' field.
      * @param value The value of 'totalLogRecords'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder setTotalLogRecords(java.lang.Long value) {
      validate(fields()[1], value);
      this.totalLogRecords = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'totalLogRecords' field has been set.
      * @return True if the 'totalLogRecords' field has been set, false otherwise.
      */
    public boolean hasTotalLogRecords() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'totalLogRecords' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder clearTotalLogRecords() {
      totalLogRecords = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'totalLogFiles' field.
      * @return The value.
      */
    public java.lang.Long getTotalLogFiles() {
      return totalLogFiles;
    }

    /**
      * Sets the value of the 'totalLogFiles' field.
      * @param value The value of 'totalLogFiles'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder setTotalLogFiles(java.lang.Long value) {
      validate(fields()[2], value);
      this.totalLogFiles = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'totalLogFiles' field has been set.
      * @return True if the 'totalLogFiles' field has been set, false otherwise.
      */
    public boolean hasTotalLogFiles() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'totalLogFiles' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder clearTotalLogFiles() {
      totalLogFiles = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'totalUpdatedRecordsCompacted' field.
      * @return The value.
      */
    public java.lang.Long getTotalUpdatedRecordsCompacted() {
      return totalUpdatedRecordsCompacted;
    }

    /**
      * Sets the value of the 'totalUpdatedRecordsCompacted' field.
      * @param value The value of 'totalUpdatedRecordsCompacted'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder setTotalUpdatedRecordsCompacted(java.lang.Long value) {
      validate(fields()[3], value);
      this.totalUpdatedRecordsCompacted = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'totalUpdatedRecordsCompacted' field has been set.
      * @return True if the 'totalUpdatedRecordsCompacted' field has been set, false otherwise.
      */
    public boolean hasTotalUpdatedRecordsCompacted() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'totalUpdatedRecordsCompacted' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder clearTotalUpdatedRecordsCompacted() {
      totalUpdatedRecordsCompacted = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'hoodieWriteStat' field.
      * @return The value.
      */
    public org.apache.hudi.avro.model.HoodieWriteStat getHoodieWriteStat() {
      return hoodieWriteStat;
    }

    /**
      * Sets the value of the 'hoodieWriteStat' field.
      * @param value The value of 'hoodieWriteStat'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder setHoodieWriteStat(org.apache.hudi.avro.model.HoodieWriteStat value) {
      validate(fields()[4], value);
      this.hoodieWriteStatBuilder = null;
      this.hoodieWriteStat = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'hoodieWriteStat' field has been set.
      * @return True if the 'hoodieWriteStat' field has been set, false otherwise.
      */
    public boolean hasHoodieWriteStat() {
      return fieldSetFlags()[4];
    }

    /**
     * Gets the Builder instance for the 'hoodieWriteStat' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public org.apache.hudi.avro.model.HoodieWriteStat.Builder getHoodieWriteStatBuilder() {
      if (hoodieWriteStatBuilder == null) {
        if (hasHoodieWriteStat()) {
          setHoodieWriteStatBuilder(org.apache.hudi.avro.model.HoodieWriteStat.newBuilder(hoodieWriteStat));
        } else {
          setHoodieWriteStatBuilder(org.apache.hudi.avro.model.HoodieWriteStat.newBuilder());
        }
      }
      return hoodieWriteStatBuilder;
    }

    /**
     * Sets the Builder instance for the 'hoodieWriteStat' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder setHoodieWriteStatBuilder(org.apache.hudi.avro.model.HoodieWriteStat.Builder value) {
      clearHoodieWriteStat();
      hoodieWriteStatBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'hoodieWriteStat' field has an active Builder instance
     * @return True if the 'hoodieWriteStat' field has an active Builder instance
     */
    public boolean hasHoodieWriteStatBuilder() {
      return hoodieWriteStatBuilder != null;
    }

    /**
      * Clears the value of the 'hoodieWriteStat' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionWriteStat.Builder clearHoodieWriteStat() {
      hoodieWriteStat = null;
      hoodieWriteStatBuilder = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HoodieCompactionWriteStat build() {
      try {
        HoodieCompactionWriteStat record = new HoodieCompactionWriteStat();
        record.partitionPath = fieldSetFlags()[0] ? this.partitionPath : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.totalLogRecords = fieldSetFlags()[1] ? this.totalLogRecords : (java.lang.Long) defaultValue(fields()[1]);
        record.totalLogFiles = fieldSetFlags()[2] ? this.totalLogFiles : (java.lang.Long) defaultValue(fields()[2]);
        record.totalUpdatedRecordsCompacted = fieldSetFlags()[3] ? this.totalUpdatedRecordsCompacted : (java.lang.Long) defaultValue(fields()[3]);
        if (hoodieWriteStatBuilder != null) {
          record.hoodieWriteStat = this.hoodieWriteStatBuilder.build();
        } else {
          record.hoodieWriteStat = fieldSetFlags()[4] ? this.hoodieWriteStat : (org.apache.hudi.avro.model.HoodieWriteStat) defaultValue(fields()[4]);
        }
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<HoodieCompactionWriteStat>
    WRITER$ = (org.apache.avro.io.DatumWriter<HoodieCompactionWriteStat>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<HoodieCompactionWriteStat>
    READER$ = (org.apache.avro.io.DatumReader<HoodieCompactionWriteStat>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
