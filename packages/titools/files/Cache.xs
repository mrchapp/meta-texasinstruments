/* --COPYRIGHT--,TI
 * Copyright (c) $(CPYYEAR)
 * Texas Instruments
 *
 *  All rights reserved.  Property of Texas Instruments
 *  Restricted rights to use, duplicate or disclose this code are
 *  granted through contract.
 * 
 * --/COPYRIGHT--*/
/*
 *  ======== Cache.xs ========
 *
 *! Revision History
 *! ================
 *! 06-Mar-2009 nitya	Push Diags and Logger settings to delegate
 *! 25-Jan-2008 agd	SDSCM00023233 Move delegate assignment into modules
 *! 25-Jan-2008 agd	created
 */

var Cache = null;

/*
 *  ======== module$use ========
 */
function module$use()
{
    Cache = this;
    Cache.common$.fxntab = false;

    if ((Cache.CacheProxy === undefined) || Cache.CacheProxy == null) {
	var Settings = xdc.module("ti.sysbios.family.Settings");
	var CacheDelegate = Settings.getDefaultCacheDelegate();
	if (CacheDelegate == null) {
            CacheDelegate = "ti.sysbios.hal.CacheNull";
	}
	Cache.CacheProxy = xdc.useModule(CacheDelegate);
    }

    /*
     * Push down common$ settings to the delegates
     */
    for (var dl in Cache.common$) {
        if (dl.match(/^diags_/) || dl.match(/^logger/)) {
	    /*
	     * Extra check below to check if CacheProxy delegate is in ROM.
	     * If delegate is in ROM, do NOT push down common$ settings
	     */
	    if (Cache.CacheProxy.delegate$.$$scope != -1) {
                Cache.CacheProxy.delegate$.common$[dl] = Cache.common$[dl];
	    }
	}
    }
}

/*
 *  ======== viewInitModule ========
 */
function viewInitModule(view, mod)
{
    var Program = xdc.useModule('xdc.rov.Program');
    CacheProxy = Program.$modules['ti.sysbios.hal.Cache'].CacheProxy;
    view.delegateCacheModule = CacheProxy.$name; 
}

