// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: add.proto

#ifndef PROTOBUF_INCLUDED_add_2eproto
#define PROTOBUF_INCLUDED_add_2eproto

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 3006000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 3006000 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/arena.h>
#include <google/protobuf/arenastring.h>
#include <google/protobuf/generated_message_table_driven.h>
#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/inlined_string_field.h>
#include <google/protobuf/metadata.h>
#include <google/protobuf/message.h>
#include <google/protobuf/repeated_field.h>  // IWYU pragma: export
#include <google/protobuf/extension_set.h>  // IWYU pragma: export
#include <google/protobuf/unknown_field_set.h>
// @@protoc_insertion_point(includes)
#define PROTOBUF_INTERNAL_EXPORT_protobuf_add_2eproto 

namespace protobuf_add_2eproto {
// Internal implementation detail -- do not use these members.
struct TableStruct {
  static const ::google::protobuf::internal::ParseTableField entries[];
  static const ::google::protobuf::internal::AuxillaryParseTableField aux[];
  static const ::google::protobuf::internal::ParseTable schema[1];
  static const ::google::protobuf::internal::FieldMetadata field_metadata[];
  static const ::google::protobuf::internal::SerializationTable serialization_table[];
  static const ::google::protobuf::uint32 offsets[];
};
void AddDescriptors();
}  // namespace protobuf_add_2eproto
class User;
class UserDefaultTypeInternal;
extern UserDefaultTypeInternal _User_default_instance_;
namespace google {
namespace protobuf {
template<> ::User* Arena::CreateMaybeMessage<::User>(Arena*);
}  // namespace protobuf
}  // namespace google

// ===================================================================

class User : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:User) */ {
 public:
  User();
  virtual ~User();

  User(const User& from);

  inline User& operator=(const User& from) {
    CopyFrom(from);
    return *this;
  }
  #if LANG_CXX11
  User(User&& from) noexcept
    : User() {
    *this = ::std::move(from);
  }

  inline User& operator=(User&& from) noexcept {
    if (GetArenaNoVirtual() == from.GetArenaNoVirtual()) {
      if (this != &from) InternalSwap(&from);
    } else {
      CopyFrom(from);
    }
    return *this;
  }
  #endif
  inline const ::google::protobuf::UnknownFieldSet& unknown_fields() const {
    return _internal_metadata_.unknown_fields();
  }
  inline ::google::protobuf::UnknownFieldSet* mutable_unknown_fields() {
    return _internal_metadata_.mutable_unknown_fields();
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const User& default_instance();

  static void InitAsDefaultInstance();  // FOR INTERNAL USE ONLY
  static inline const User* internal_default_instance() {
    return reinterpret_cast<const User*>(
               &_User_default_instance_);
  }
  static constexpr int kIndexInFileMessages =
    0;

  void Swap(User* other);
  friend void swap(User& a, User& b) {
    a.Swap(&b);
  }

  // implements Message ----------------------------------------------

  inline User* New() const final {
    return CreateMaybeMessage<User>(NULL);
  }

  User* New(::google::protobuf::Arena* arena) const final {
    return CreateMaybeMessage<User>(arena);
  }
  void CopyFrom(const ::google::protobuf::Message& from) final;
  void MergeFrom(const ::google::protobuf::Message& from) final;
  void CopyFrom(const User& from);
  void MergeFrom(const User& from);
  void Clear() final;
  bool IsInitialized() const final;

  size_t ByteSizeLong() const final;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input) final;
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const final;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* target) const final;
  int GetCachedSize() const final { return _cached_size_.Get(); }

  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const final;
  void InternalSwap(User* other);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return NULL;
  }
  inline void* MaybeArenaPtr() const {
    return NULL;
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const final;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // optional string nickName = 2;
  bool has_nickname() const;
  void clear_nickname();
  static const int kNickNameFieldNumber = 2;
  const ::std::string& nickname() const;
  void set_nickname(const ::std::string& value);
  #if LANG_CXX11
  void set_nickname(::std::string&& value);
  #endif
  void set_nickname(const char* value);
  void set_nickname(const char* value, size_t size);
  ::std::string* mutable_nickname();
  ::std::string* release_nickname();
  void set_allocated_nickname(::std::string* nickname);

  // optional uint64 id = 1;
  bool has_id() const;
  void clear_id();
  static const int kIdFieldNumber = 1;
  ::google::protobuf::uint64 id() const;
  void set_id(::google::protobuf::uint64 value);

  // @@protoc_insertion_point(class_scope:User)
 private:
  void set_has_id();
  void clear_has_id();
  void set_has_nickname();
  void clear_has_nickname();

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::HasBits<1> _has_bits_;
  mutable ::google::protobuf::internal::CachedSize _cached_size_;
  ::google::protobuf::internal::ArenaStringPtr nickname_;
  ::google::protobuf::uint64 id_;
  friend struct ::protobuf_add_2eproto::TableStruct;
};
// ===================================================================


// ===================================================================

#ifdef __GNUC__
  #pragma GCC diagnostic push
  #pragma GCC diagnostic ignored "-Wstrict-aliasing"
#endif  // __GNUC__
// User

// optional uint64 id = 1;
inline bool User::has_id() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
inline void User::set_has_id() {
  _has_bits_[0] |= 0x00000002u;
}
inline void User::clear_has_id() {
  _has_bits_[0] &= ~0x00000002u;
}
inline void User::clear_id() {
  id_ = GOOGLE_ULONGLONG(0);
  clear_has_id();
}
inline ::google::protobuf::uint64 User::id() const {
  // @@protoc_insertion_point(field_get:User.id)
  return id_;
}
inline void User::set_id(::google::protobuf::uint64 value) {
  set_has_id();
  id_ = value;
  // @@protoc_insertion_point(field_set:User.id)
}

// optional string nickName = 2;
inline bool User::has_nickname() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void User::set_has_nickname() {
  _has_bits_[0] |= 0x00000001u;
}
inline void User::clear_has_nickname() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void User::clear_nickname() {
  nickname_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_nickname();
}
inline const ::std::string& User::nickname() const {
  // @@protoc_insertion_point(field_get:User.nickName)
  return nickname_.GetNoArena();
}
inline void User::set_nickname(const ::std::string& value) {
  set_has_nickname();
  nickname_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:User.nickName)
}
#if LANG_CXX11
inline void User::set_nickname(::std::string&& value) {
  set_has_nickname();
  nickname_.SetNoArena(
    &::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::move(value));
  // @@protoc_insertion_point(field_set_rvalue:User.nickName)
}
#endif
inline void User::set_nickname(const char* value) {
  GOOGLE_DCHECK(value != NULL);
  set_has_nickname();
  nickname_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:User.nickName)
}
inline void User::set_nickname(const char* value, size_t size) {
  set_has_nickname();
  nickname_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:User.nickName)
}
inline ::std::string* User::mutable_nickname() {
  set_has_nickname();
  // @@protoc_insertion_point(field_mutable:User.nickName)
  return nickname_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* User::release_nickname() {
  // @@protoc_insertion_point(field_release:User.nickName)
  if (!has_nickname()) {
    return NULL;
  }
  clear_has_nickname();
  return nickname_.ReleaseNonDefaultNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void User::set_allocated_nickname(::std::string* nickname) {
  if (nickname != NULL) {
    set_has_nickname();
  } else {
    clear_has_nickname();
  }
  nickname_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), nickname);
  // @@protoc_insertion_point(field_set_allocated:User.nickName)
}

#ifdef __GNUC__
  #pragma GCC diagnostic pop
#endif  // __GNUC__

// @@protoc_insertion_point(namespace_scope)


// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_INCLUDED_add_2eproto
