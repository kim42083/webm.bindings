// Author: mszal@google.com (Michael Szal)

package com.google.libwebm.mkvmuxer;

import com.google.libwebm.Common;

public class Segment extends Common {

  public enum Mode {
    None,
    kLive,
    kFile
  }

  public static final long kDefaultMaxClusterDuration = 30000000000L;

  public Segment() {
    nativePointer = newSegment();
  }

  public long addAudioTrack(int sampleRate, int channels, int number) {
    return AddAudioTrack(nativePointer, sampleRate, channels, number);
  }

  public Chapter addChapter() {
    long pointer = AddChapter(nativePointer);
    return new Chapter(pointer);
  }

  public boolean addCuePoint(long timestamp, long track) {
    return AddCuePoint(nativePointer, timestamp, track);
  }

  public boolean addFrame(byte[] frame, long trackNumber, long timestampNs, boolean isKey) {
    return AddFrame(nativePointer, frame, frame.length, trackNumber, timestampNs, isKey);
  }

  public boolean addMetadata(byte[] frame, long trackNumber, long timestampNs, long durationNs) {
    return AddMetadata(nativePointer, frame, frame.length, trackNumber, timestampNs, durationNs);
  }

  public Track addTrack(int number) {
    long pointer = AddTrack(nativePointer, number);
    return Track.newTrack(pointer);
  }

  public long addVideoTrack(int width, int height, int number) {
    return AddVideoTrack(nativePointer, width, height, number);
  }

  public boolean chunking() {
    return chunking(nativePointer);
  }

  public long cuesTrack() {
    return getCuesTrack(nativePointer);
  }

  public boolean cuesTrack(long trackNumber) {
    return setCuesTrack(nativePointer, trackNumber);
  }

  public boolean finalizeSegment() {
    return Finalize(nativePointer);
  }

  public void forceNewClusterOnNextFrame() {
    ForceNewClusterOnNextFrame(nativePointer);
  }

  public Cues getCues() {
    long pointer = GetCues(nativePointer);
    return new Cues(pointer);
  }

  public SegmentInfo getSegmentInfo() {
    long pointer = GetSegmentInfo(nativePointer);
    return new SegmentInfo(pointer);
  }

  public Track getTrackByNumber(long trackNumber) {
    long pointer = GetTrackByNumber(nativePointer, trackNumber);
    return Track.newTrack(pointer);
  }

  public boolean init(IMkvWriter writer) {
    return Init(nativePointer, writer.getNativePointer());
  }

  public long maxClusterDuration() {
    return maxClusterDuration(nativePointer);
  }

  public long maxClusterSize() {
    return maxClusterSize(nativePointer);
  }

  public Mode mode() {
    int ordinal = mode(nativePointer);
    return Mode.values()[ordinal];
  }

  public boolean outputCues() {
    return outputCues(nativePointer);
  }

  public void outputCues(boolean outputCues) {
    OutputCues(nativePointer, outputCues);
  }

  public SegmentInfo segmentInfo() {
    long pointer = segmentInfo(nativePointer);
    return new SegmentInfo(pointer);
  }

  public void setMaxClusterDuration(long maxClusterDuration) {
    setMaxClusterDuration(nativePointer, maxClusterDuration);
  }

  public void setMaxClusterSize(long maxClusterSize) {
    setMaxClusterSize(nativePointer, maxClusterSize);
  }

  public void setMode(Mode mode) {
    setMode(nativePointer, mode.ordinal());
  }

  public boolean setChunking(boolean chunking, String filename) {
    return SetChunking(nativePointer, chunking, filename);
  }

  protected Segment(long nativePointer) {
    super(nativePointer);
  }

  protected void deleteObject() {
    deleteSegment(nativePointer);
  }

  private native long AddAudioTrack(long jSegment, int sample_rate, int channels, int number);
  private native long AddChapter(long jSegment);
  private native boolean AddCuePoint(long jSegment, long timestamp, long track);
  private native boolean AddFrame(long jSegment, byte[] jFrame, long length, long track_number,
      long timestamp_ns, boolean is_key);
  private native boolean AddMetadata(long jSegment, byte[] jFrame, long length, long track_number,
      long timestamp_ns, long duration_ns);
  private native long AddTrack(long jSegment, int number);
  private native long AddVideoTrack(long jSegment, int width, int height, int number);
  private native boolean chunking(long jSegment);
  private native void deleteSegment(long jSegment);
  private native boolean Finalize(long jSegment);
  private native void ForceNewClusterOnNextFrame(long jSegment);
  private native long GetCues(long jSegment);
  private native long getCuesTrack(long jSegment);
  private native long GetSegmentInfo(long jSegment);
  private native long GetTrackByNumber(long jSegment, long track_number);
  private native boolean Init(long jSegment, long jWriter);
  private native long maxClusterDuration(long jSegment);
  private native long maxClusterSize(long jSegment);
  private native int mode(long jSegment);
  private native long newSegment();
  private native boolean outputCues(long jSegment);
  private native void OutputCues(long jSegment, boolean output_cues);
  private native long segmentInfo(long jSegment);
  private native boolean setCuesTrack(long jSegment, long track_number);
  private native void setMaxClusterDuration(long jSegment, long max_cluster_duration);
  private native void setMaxClusterSize(long jSegment, long max_cluster_size);
  private native void setMode(long jSegment, int mode);
  private native boolean SetChunking(long jSegment, boolean chunking, String jFilename);
}