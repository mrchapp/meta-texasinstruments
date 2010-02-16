/*TI_IPC_Api.h
 *
 * Copyright 2001-2009 Texas Instruments, Inc. - http://www.ti.com/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 * Alternatively, this software may be distributed under the terms of BSD
 * license.
 *
 * See README and COPYING for more details.
 */

#ifndef _TI_IPC_API_H
#define _TI_IPC_API_H

#define MAX_EVENT_DATA_SIZE 128
#define DOT11_WSC_PROBE_REQ_MAX_LENGTH 80
#define PRIVATE_CMD_SET_FLAG    0x00000001
#define SET_BIT		 0x08000000	
#define GET_BIT      0x00800000
#define SITE_MGR_MODULE_PARAM   0x0600
#define SITE_MGR_SIMPLE_CONFIG_MODE  SET_BIT | GET_BIT | SITE_MGR_MODULE_PARAM | 0x38

#ifdef  __cplusplus
extern "C" {
#endif

/*******************Defines*********************/

/* WARNING! DON'T CHANGE THE ORDER OF EVENTS! */
/* OS EVENTS MUST COME FIRST!*/

enum
{
    IPC_EVENT_ASSOCIATED = 0,
    IPC_EVENT_DISASSOCIATED,
    IPC_EVENT_LINK_SPEED,
    IPC_EVENT_AUTH_SUCC,
    IPC_EVENT_SCAN_REPORT,
    IPC_EVENT_SCAN_COMPLETE,
    IPC_EVENT_SCAN_STOPPED,
    IPC_EVENT_CCKM_START,
    IPC_EVENT_MEDIA_SPECIFIC,
    IPC_EVENT_MAX_OS_EVENT = IPC_EVENT_MEDIA_SPECIFIC,
    IPC_EVENT_EAPOL,
    IPC_EVENT_BOUND,
    IPC_EVENT_UNBOUND,
    IPC_EVENT_PREAUTH_EAPOL,
    IPC_EVENT_RESERVED2,
    IPC_EVENT_LOW_RSSI,
    IPC_EVENT_TSPEC_STATUS,
    IPC_EVENT_TSPEC_RATE_STATUS,
    IPC_EVENT_MEDIUM_TIME_CROSS,
    IPC_EVENT_ROAMING_COMPLETE,
    IPC_EVENT_EAP_AUTH_FAILURE,
    IPC_EVENT_WPA2_PREAUTHENTICATION,
    IPC_EVENT_TRAFFIC_INTENSITY_THRESHOLD_CROSSED,
    IPC_EVENT_SCAN_FAILED,
    IPC_EVENT_WPS_SESSION_OVERLAP,
    IPC_EVENT_RSSI_SNR_TRIGGER,
    IPC_EVENT_RSSI_SNR_TRIGGER_0,
	IPC_EVENT_LOGGER,
    IPC_EVENT_NOT_ASSOCIATED,
    IPC_EVENT_BSS_LOSS,
    IPC_EVENT_REASSOCIATION_RESP,
    IPC_EVENT_IMMEDIATE_SCAN_REPORT,
    IPC_EVENT_CONTINUOUS_SCAN_REPORT,
    IPC_EVENT_RSSI_SNR_TRIGGER_1,
    IPC_EVENT_AP_DISCONNECT,
    IPC_EVENT_TX_RETRY_FALIURE,
    IPC_EVENT_RE_AUTH_STARTED,
    IPC_EVENT_RE_AUTH_COMPLETED,
    IPC_EVENT_RE_AUTH_TERMINATED,
    IPC_EVENT_TIMEOUT,
    IPC_EVENT_GWSI,
    IPC_EVENT_MAX
};

/************************* Events Functions *******************************/

typedef struct _IPC_EV_DATA * PIPC_EV_DATA;  

typedef unsigned int (*TI_EVENT_CALLBACK) (PIPC_EV_DATA  pData);

typedef struct _IPC_EVENT_PARAMS
{
    unsigned int         uEventType;
    void*            	 uEventID;
    unsigned int         uProcessID;
    unsigned int         uDeliveryType;
    void*           hUserParam;            /* Handle to back reference*/
    TI_EVENT_CALLBACK   pfEventCallback;
}IPC_EVENT_PARAMS;

/* EvParams are assumed to be the first field. Any addtions shoild be made 
    afterwards
 */
typedef struct _IPC_EV_DATA
{
    IPC_EVENT_PARAMS    EvParams;
    unsigned int        uBufferSize;
    unsigned char       uBuffer[MAX_EVENT_DATA_SIZE];
}IPC_EV_DATA;

typedef struct
{
 		unsigned int       cmd;                    /**< Number of command to execute - configMgr parameter name                    */
        unsigned int       flags;                  /**< Command action type (PRIVATE_CMD_SET_FLAG | PRIVATE_CMD_GET_FLAG)  */
        void*           in_buffer;              /**< Pointer to Input Buffer                                                                                    */
        unsigned int       in_buffer_len;  /**< Input buffer length                                                                                                */
        void*           out_buffer;             /**< Pointer to Output buffer                                                                                   */
        unsigned int       out_buffer_len; /**< Output buffer length                                                                                               */
} private_cmd_t;


/** \enum TIWLN_SIMPLE_CONFIG_MODE
 *  * \brief TI WLAN Simple Configuration Mode
 *   *
 *    * \par Description
 *     * Used for indicating WiFi Simple Configuration mode
 *      *
 *       * \sa
 *        */
typedef enum
{
    TIWLN_SIMPLE_CONFIG_OFF = 0,                /**< Simple Configuration OFF                   */
    TIWLN_SIMPLE_CONFIG_PIN_METHOD,             /**< Simple Configuration PIN Method    */
    TIWLN_SIMPLE_CONFIG_PBC_METHOD              /**< Simple Configuration PBC Method    */
} TIWLN_SIMPLE_CONFIG_MODE;


/** \struct TWscMode
 *  * \brief WSC Mode
 *   *
 *    * \par Description
 *     * This structure is used whenever the WiFi Simple Configuration Mode is modified between ON and OFF.
 *      * Upon enabling the Simple Configuration, the user must fill the probeReqWSCIE fields
 *       *
 *        * \sa
 *         */
typedef struct
{
    TIWLN_SIMPLE_CONFIG_MODE  WSCMode;                                          /**< WiFi Simple Configuration mode                                             */
    unsigned int uWscIeSize;                                                           /**< Simple Config IE actual size (the part after the OUI) */
    unsigned char probeReqWSCIE[DOT11_WSC_PROBE_REQ_MAX_LENGTH];     /**< Buffer which holds the parameters of ProbeReq - WSC IE     */
}  TWscMode;


#ifdef  __cplusplus
}
#endif

#endif /*_IPC_UTIL_H*/

