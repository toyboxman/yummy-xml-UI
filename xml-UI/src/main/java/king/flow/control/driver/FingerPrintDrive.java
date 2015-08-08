/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.driver;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 *
 * @author Administrator
 */
public interface FingerPrintDrive extends Library {

    FingerPrintDrive INSTANCE = (FingerPrintDrive) Native.loadLibrary("TesoLive", FingerPrintDrive.class);

    int TcCreateHDL(int nPort, int nProt, int nRidx, int nSped);

    int TcDeleteHDL(int vHdl);

    int TcExtnEntry(int vHdl, int nRead, int nIdx,
            int nVal, byte[] hVoid);

    int TcDoFeature(int vHdl, byte[] hFea);

    int TcDoTemplet(int vHdl, byte[] hTpl);

    int TcSafeMatch(int vHdl, String hFea,
            String hTpl, int nLvl);

    int TcGetImgDat(int vHdl, int nIdx, byte[] hFpr);

    int TcFeaFrmImg(int vHdl, byte[] hFpr, byte[] hFea);

    int TcTplFrmImg(int vHdl, byte[] hFp0, byte[] hFp1,
            byte[] hFp2, byte[] hTpl);

    int TcReadDevSn(int vHdl, byte[] chSn);

    int TcOperFlash(int vHdl, int nRead, int nOfs,
            byte[] hBuf, int nLen);

    int TcBeepLight(int vHdl, int nMode);

    int TcIniListen(int vHdl, int hFunc, int nPara);

    int TcWhereAreu(int nBgn, int nEnd, int nRidx, int nMask,
            String chFix);

    int TcCodeCnvrt(int bEnc, int nFmt, byte[] hDst,
            byte[] hSrc, int nLen);

}
