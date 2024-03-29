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
public class HoodieBootstrapFilePartitionInfo extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 3264734783275825247L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieBootstrapFilePartitionInfo\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"bootstrapPartitionPath\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"bootstrapFileStatus\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieFileStatus\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"path\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodiePath\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"uri\",\"type\":[\"null\",\"string\"],\"default\":null}]}],\"default\":null},{\"name\":\"length\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"isDir\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"blockReplication\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"blockSize\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"modificationTime\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"accessTime\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"permission\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieFSPermission\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"userAction\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"groupAction\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"otherAction\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"stickyBit\",\"type\":[\"null\",\"boolean\"],\"default\":null}]}],\"default\":null},{\"name\":\"owner\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"group\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"symlink\",\"type\":[\"null\",\"HoodiePath\"],\"default\":null}]}],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<HoodieBootstrapFilePartitionInfo> ENCODER =
      new BinaryMessageEncoder<HoodieBootstrapFilePartitionInfo>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<HoodieBootstrapFilePartitionInfo> DECODER =
      new BinaryMessageDecoder<HoodieBootstrapFilePartitionInfo>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<HoodieBootstrapFilePartitionInfo> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<HoodieBootstrapFilePartitionInfo> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<HoodieBootstrapFilePartitionInfo>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this HoodieBootstrapFilePartitionInfo to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a HoodieBootstrapFilePartitionInfo from a ByteBuffer. */
  public static HoodieBootstrapFilePartitionInfo fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.Integer version;
  @Deprecated public java.lang.CharSequence bootstrapPartitionPath;
  @Deprecated public org.apache.hudi.avro.model.HoodieFileStatus bootstrapFileStatus;
  @Deprecated public java.lang.CharSequence partitionPath;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public HoodieBootstrapFilePartitionInfo() {}

  /**
   * All-args constructor.
   * @param version The new value for version
   * @param bootstrapPartitionPath The new value for bootstrapPartitionPath
   * @param bootstrapFileStatus The new value for bootstrapFileStatus
   * @param partitionPath The new value for partitionPath
   */
  public HoodieBootstrapFilePartitionInfo(java.lang.Integer version, java.lang.CharSequence bootstrapPartitionPath, org.apache.hudi.avro.model.HoodieFileStatus bootstrapFileStatus, java.lang.CharSequence partitionPath) {
    this.version = version;
    this.bootstrapPartitionPath = bootstrapPartitionPath;
    this.bootstrapFileStatus = bootstrapFileStatus;
    this.partitionPath = partitionPath;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return version;
    case 1: return bootstrapPartitionPath;
    case 2: return bootstrapFileStatus;
    case 3: return partitionPath;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: version = (java.lang.Integer)value$; break;
    case 1: bootstrapPartitionPath = (java.lang.CharSequence)value$; break;
    case 2: bootstrapFileStatus = (org.apache.hudi.avro.model.HoodieFileStatus)value$; break;
    case 3: partitionPath = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
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
   * Gets the value of the 'bootstrapPartitionPath' field.
   * @return The value of the 'bootstrapPartitionPath' field.
   */
  public java.lang.CharSequence getBootstrapPartitionPath() {
    return bootstrapPartitionPath;
  }

  /**
   * Sets the value of the 'bootstrapPartitionPath' field.
   * @param value the value to set.
   */
  public void setBootstrapPartitionPath(java.lang.CharSequence value) {
    this.bootstrapPartitionPath = value;
  }

  /**
   * Gets the value of the 'bootstrapFileStatus' field.
   * @return The value of the 'bootstrapFileStatus' field.
   */
  public org.apache.hudi.avro.model.HoodieFileStatus getBootstrapFileStatus() {
    return bootstrapFileStatus;
  }

  /**
   * Sets the value of the 'bootstrapFileStatus' field.
   * @param value the value to set.
   */
  public void setBootstrapFileStatus(org.apache.hudi.avro.model.HoodieFileStatus value) {
    this.bootstrapFileStatus = value;
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
   * Creates a new HoodieBootstrapFilePartitionInfo RecordBuilder.
   * @return A new HoodieBootstrapFilePartitionInfo RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder newBuilder() {
    return new org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder();
  }

  /**
   * Creates a new HoodieBootstrapFilePartitionInfo RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new HoodieBootstrapFilePartitionInfo RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder newBuilder(org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder other) {
    return new org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder(other);
  }

  /**
   * Creates a new HoodieBootstrapFilePartitionInfo RecordBuilder by copying an existing HoodieBootstrapFilePartitionInfo instance.
   * @param other The existing instance to copy.
   * @return A new HoodieBootstrapFilePartitionInfo RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder newBuilder(org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo other) {
    return new org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder(other);
  }

  /**
   * RecordBuilder for HoodieBootstrapFilePartitionInfo instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<HoodieBootstrapFilePartitionInfo>
    implements org.apache.avro.data.RecordBuilder<HoodieBootstrapFilePartitionInfo> {

    private java.lang.Integer version;
    private java.lang.CharSequence bootstrapPartitionPath;
    private org.apache.hudi.avro.model.HoodieFileStatus bootstrapFileStatus;
    private org.apache.hudi.avro.model.HoodieFileStatus.Builder bootstrapFileStatusBuilder;
    private java.lang.CharSequence partitionPath;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.version)) {
        this.version = data().deepCopy(fields()[0].schema(), other.version);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.bootstrapPartitionPath)) {
        this.bootstrapPartitionPath = data().deepCopy(fields()[1].schema(), other.bootstrapPartitionPath);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.bootstrapFileStatus)) {
        this.bootstrapFileStatus = data().deepCopy(fields()[2].schema(), other.bootstrapFileStatus);
        fieldSetFlags()[2] = true;
      }
      if (other.hasBootstrapFileStatusBuilder()) {
        this.bootstrapFileStatusBuilder = org.apache.hudi.avro.model.HoodieFileStatus.newBuilder(other.getBootstrapFileStatusBuilder());
      }
      if (isValidValue(fields()[3], other.partitionPath)) {
        this.partitionPath = data().deepCopy(fields()[3].schema(), other.partitionPath);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing HoodieBootstrapFilePartitionInfo instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.version)) {
        this.version = data().deepCopy(fields()[0].schema(), other.version);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.bootstrapPartitionPath)) {
        this.bootstrapPartitionPath = data().deepCopy(fields()[1].schema(), other.bootstrapPartitionPath);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.bootstrapFileStatus)) {
        this.bootstrapFileStatus = data().deepCopy(fields()[2].schema(), other.bootstrapFileStatus);
        fieldSetFlags()[2] = true;
      }
      this.bootstrapFileStatusBuilder = null;
      if (isValidValue(fields()[3], other.partitionPath)) {
        this.partitionPath = data().deepCopy(fields()[3].schema(), other.partitionPath);
        fieldSetFlags()[3] = true;
      }
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
    public org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder setVersion(java.lang.Integer value) {
      validate(fields()[0], value);
      this.version = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'version' field has been set.
      * @return True if the 'version' field has been set, false otherwise.
      */
    public boolean hasVersion() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'version' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder clearVersion() {
      version = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'bootstrapPartitionPath' field.
      * @return The value.
      */
    public java.lang.CharSequence getBootstrapPartitionPath() {
      return bootstrapPartitionPath;
    }

    /**
      * Sets the value of the 'bootstrapPartitionPath' field.
      * @param value The value of 'bootstrapPartitionPath'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder setBootstrapPartitionPath(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.bootstrapPartitionPath = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'bootstrapPartitionPath' field has been set.
      * @return True if the 'bootstrapPartitionPath' field has been set, false otherwise.
      */
    public boolean hasBootstrapPartitionPath() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'bootstrapPartitionPath' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder clearBootstrapPartitionPath() {
      bootstrapPartitionPath = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'bootstrapFileStatus' field.
      * @return The value.
      */
    public org.apache.hudi.avro.model.HoodieFileStatus getBootstrapFileStatus() {
      return bootstrapFileStatus;
    }

    /**
      * Sets the value of the 'bootstrapFileStatus' field.
      * @param value The value of 'bootstrapFileStatus'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder setBootstrapFileStatus(org.apache.hudi.avro.model.HoodieFileStatus value) {
      validate(fields()[2], value);
      this.bootstrapFileStatusBuilder = null;
      this.bootstrapFileStatus = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'bootstrapFileStatus' field has been set.
      * @return True if the 'bootstrapFileStatus' field has been set, false otherwise.
      */
    public boolean hasBootstrapFileStatus() {
      return fieldSetFlags()[2];
    }

    /**
     * Gets the Builder instance for the 'bootstrapFileStatus' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public org.apache.hudi.avro.model.HoodieFileStatus.Builder getBootstrapFileStatusBuilder() {
      if (bootstrapFileStatusBuilder == null) {
        if (hasBootstrapFileStatus()) {
          setBootstrapFileStatusBuilder(org.apache.hudi.avro.model.HoodieFileStatus.newBuilder(bootstrapFileStatus));
        } else {
          setBootstrapFileStatusBuilder(org.apache.hudi.avro.model.HoodieFileStatus.newBuilder());
        }
      }
      return bootstrapFileStatusBuilder;
    }

    /**
     * Sets the Builder instance for the 'bootstrapFileStatus' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder setBootstrapFileStatusBuilder(org.apache.hudi.avro.model.HoodieFileStatus.Builder value) {
      clearBootstrapFileStatus();
      bootstrapFileStatusBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'bootstrapFileStatus' field has an active Builder instance
     * @return True if the 'bootstrapFileStatus' field has an active Builder instance
     */
    public boolean hasBootstrapFileStatusBuilder() {
      return bootstrapFileStatusBuilder != null;
    }

    /**
      * Clears the value of the 'bootstrapFileStatus' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder clearBootstrapFileStatus() {
      bootstrapFileStatus = null;
      bootstrapFileStatusBuilder = null;
      fieldSetFlags()[2] = false;
      return this;
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
    public org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder setPartitionPath(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.partitionPath = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'partitionPath' field has been set.
      * @return True if the 'partitionPath' field has been set, false otherwise.
      */
    public boolean hasPartitionPath() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'partitionPath' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieBootstrapFilePartitionInfo.Builder clearPartitionPath() {
      partitionPath = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HoodieBootstrapFilePartitionInfo build() {
      try {
        HoodieBootstrapFilePartitionInfo record = new HoodieBootstrapFilePartitionInfo();
        record.version = fieldSetFlags()[0] ? this.version : (java.lang.Integer) defaultValue(fields()[0]);
        record.bootstrapPartitionPath = fieldSetFlags()[1] ? this.bootstrapPartitionPath : (java.lang.CharSequence) defaultValue(fields()[1]);
        if (bootstrapFileStatusBuilder != null) {
          record.bootstrapFileStatus = this.bootstrapFileStatusBuilder.build();
        } else {
          record.bootstrapFileStatus = fieldSetFlags()[2] ? this.bootstrapFileStatus : (org.apache.hudi.avro.model.HoodieFileStatus) defaultValue(fields()[2]);
        }
        record.partitionPath = fieldSetFlags()[3] ? this.partitionPath : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<HoodieBootstrapFilePartitionInfo>
    WRITER$ = (org.apache.avro.io.DatumWriter<HoodieBootstrapFilePartitionInfo>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<HoodieBootstrapFilePartitionInfo>
    READER$ = (org.apache.avro.io.DatumReader<HoodieBootstrapFilePartitionInfo>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
