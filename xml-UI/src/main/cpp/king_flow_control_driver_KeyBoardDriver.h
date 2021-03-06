/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class king_flow_control_driver_KeyBoardDriver */

#ifndef _Included_king_flow_control_driver_KeyBoardDriver
#define _Included_king_flow_control_driver_KeyBoardDriver
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     king_flow_control_driver_KeyBoardDriver
 * Method:    downloadSecretKey
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_king_flow_control_driver_KeyBoardDriver_downloadSecretKey
  (JNIEnv *, jobject, jstring, jstring, jstring, jstring);

/*
 * Class:     king_flow_control_driver_KeyBoardDriver
 * Method:    openPin
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_king_flow_control_driver_KeyBoardDriver_openPin
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     king_flow_control_driver_KeyBoardDriver
 * Method:    readPin
 * Signature: ()C
 */
JNIEXPORT jchar JNICALL Java_king_flow_control_driver_KeyBoardDriver_readPin
  (JNIEnv *, jobject);

/*
 * Class:     king_flow_control_driver_KeyBoardDriver
 * Method:    closePin
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_king_flow_control_driver_KeyBoardDriver_closePin
  (JNIEnv *, jobject);

/*
 * Class:     king_flow_control_driver_KeyBoardDriver
 * Method:    getPin
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_king_flow_control_driver_KeyBoardDriver_getPin
  (JNIEnv *, jobject, jstring, jstring, jstring);

/*
 * Class:     king_flow_control_driver_KeyBoardDriver
 * Method:    OpenComm
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_king_flow_control_driver_KeyBoardDriver_OpenComm
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     king_flow_control_driver_KeyBoardDriver
 * Method:    ScanKeyPress
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_king_flow_control_driver_KeyBoardDriver_ScanKeyPress
  (JNIEnv *, jobject, jstring);

/*
 * Class:     king_flow_control_driver_KeyBoardDriver
 * Method:    GetPinblock
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_king_flow_control_driver_KeyBoardDriver_GetPinblock
  (JNIEnv *, jobject, jstring, jstring, jstring);

/*
 * Class:     king_flow_control_driver_KeyBoardDriver
 * Method:    CloseComm
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_king_flow_control_driver_KeyBoardDriver_CloseComm
  (JNIEnv *, jobject, jstring, jstring);

#ifdef __cplusplus
}
#endif
#endif
