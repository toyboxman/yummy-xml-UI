/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class king_flow_control_driver_PrinterConductor */

#ifndef _Included_king_flow_control_driver_PrinterConductor
#define _Included_king_flow_control_driver_PrinterConductor
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     king_flow_control_driver_PrinterConductor
 * Method:    printHead
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_king_flow_control_driver_PrinterConductor_printHead
  (JNIEnv *, jobject, jstring, jstring, jstring);

/*
 * Class:     king_flow_control_driver_PrinterConductor
 * Method:    printBody
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_king_flow_control_driver_PrinterConductor_printBody
  (JNIEnv *, jobject, jstring, jstring, jstring);

/*
 * Class:     king_flow_control_driver_PrinterConductor
 * Method:    printTail
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_king_flow_control_driver_PrinterConductor_printTail
  (JNIEnv *, jobject, jstring, jstring, jstring);

/*
 * Class:     king_flow_control_driver_PrinterConductor
 * Method:    printState
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_king_flow_control_driver_PrinterConductor_printState
  (JNIEnv *, jobject, jstring, jstring);

#ifdef __cplusplus
}
#endif
#endif
