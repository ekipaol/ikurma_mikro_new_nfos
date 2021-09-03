package com.application.bris.ikurma.api.config;

import com.application.bris.ikurma.BuildConfig;

/**
 * Created by PID on 4/5/2019.
 */

public class UriApi {




    public static class Baseurl{

//        public static final String URLDEV = "http://10.1.25.55:8080/MobileBRISIAPI-WILLY8/webresources/"; //DEV
//        public static final String URLDEV = "http://10.0.1.210:8080/MobileBRISIAPI/webresources/"; //DEV
//        public static final String URLDEV = "http://10.0.116.37:8054/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/"; //DEV
        public static final String URLDEV = "http://10.0.116.37:8054"; //DEV
//        public static final String URLDEV = "https://10.0.116.105/NOS_MIKRO_PROC/rest/API_NOS/";

        public static final String URLPROD = "https://intel.brisyariah.co.id:55056/MobileBRISIAPI/webresources/"; //PROD
        public static String URL = (BuildConfig.IS_PRODUCTION) ? URLPROD : URLDEV ; //ENV BASED URI SELECTOR



        public static final String URL_MAPS = "https://api.opencagedata.com/";
    }

    public class general {
        public static final String searchAddress = "generic/pencarianKodePos";
        public static final String login = "generic/login";
        public static final String login2 = "generic/loginn";
        public static final String home = "generic/dashboard";
        public static final String getProduct = "generic/getProduk";
        public static final String getProductAmanah = "generic/getProduk";
        public static final String getKategSektorEkonomii = "generic/mikro/hotprospek/datapembiayaan/kategoriSektorEkonomi";
        public static final String searcSektorEkonomi = "generic/mikro/hotprospek/datapembiayaan/pencarianSektorEkonomi";
        public static final String listDeviasi = "generic/mikro/hotprospek/kirimPutusan/listDeviasiMikro";
        public static final String activation = "generic/aktivasi/activate";
        public static final String checkUpdate = "generic/getAppVersion";
        public static final String geocoding = "geocode/v1/google-v3-json?";
        public static final String updateFirebase = "generic/aktivasi/updateFirebaseMessagingID";
        public static final String brisnotifRegister = "generic/brisnotifRegister";

    }

    public class generalNos {

        public static final String login = "/user-service/logins";
        public static final String login2 = "/API_NOS/user-service/logins";
        public static final String searchAddress = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/pencarianKodepos";
        public static final String home = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/dashboard";
        public static final String getProduct = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/getProduk";
        public static final String getProductAmanah = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/getProduk";
        public static final String getKategSektorEkonomii = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/kategoriSektorEkonomi";
        public static final String searcSektorEkonomi = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/pencarianSektorEkonomi";
        public static final String listDeviasi = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/KirimPutusan/ListDeviasi";
        public static final String geocoding = "geocode/v1/google-v3-json?";


    }

    public class pipeline {
        public static final String listPipeline = "generic/mikro/pipeline/listPipeline";
        public static final String inquiryPipeline = "generic/mikro/pipeline/inquirePipeline";
        public static final String uploadFoto = "generic/uploadImage";
        public static final String sendDataPipeline = "generic/mikro/pipeline/updatePipeline";
        public static final String pipelineToHotprospek = "generic/mikro/pipeline/kirimHotProspek";
        public static final String rejectPipeline = "tolakPipeline";
        public static final String savePipelineHotprospek = "generic/mikro/pipeline/simpanDanKirimHotProspek";

        //multifaedahmikro
        public static final String listPipelineKmg = "generic/mikro/konsumer/pipeline/listPipeline";
        public static final String inquiryPipelineKmg = "generic/mikro/konsumer/pipeline/inquirePipeline";
        public static final String getTujuanPenggunaan = "generic/mikro/konsumer/pipeline/listTujuanPenggunaan";
        public static final String getListInstansi = "generic/mikro/konsumer/pipeline/listInstansi";
        public static final String sendDataPipelineKmg = "generic/mikro/konsumer/pipeline/updatePipeline";
        public static final String pipelineToHotprospekKmg = "generic/mikro/konsumer/pipeline/kirimHotProspek";
        public static final String rejectPipelineKmg = "generic/mikro/konsumer/pipeline/tolak";
        public static final String savePipelineHotprospekKmg = "generic/mikro/konsumer/pipeline/simpanDanKirimHotProspek";

        //Purna
        public static final String listProgram = "generic/mikro/konsumer/pipeline/listProduct";
        public static final String listInstitusi = "generic/mikro/konsumer/pipeline/listInstitusi";
        public static final String listRekananDM = "generic/mikro/konsumer/pipeline/listRekananDM";
        public static final String listKategNasabah = "generic/mikro/konsumer/pipeline/listKategNasabah";
    }

    public class pipelineNos {
        public static final String listPipeline = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/ListPipeline";
        public static final String inquiryPipeline = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/inquirePipeline";
        public static final String sendDataPipeline = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/UpdatePipeline";
        public static final String pipelineToHotprospek = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/KirimHotprospek";
        public static final String rejectPipeline = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/tolakPipeline";
        public static final String savePipelineHotprospek = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/simpanDanKirimHotprospek";
        public static final String uploadFoto = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/UploadImage";

    }


    public class hotprospek {
        public static final String listHotprospek = "generic/mikro/hotprospek/listHotProspek";
        public static final String inquiryHotprospek = "generic/mikro/hotprospek/inquireHotprospek";
        public static final String sendDataHotprospek = "generic/mikro/hotprospek/updateHotprospek";
        public static final String rejectHotprospek = "generic/mikro/hotprospek/tolakHotprospek";

        public static final String inquiryDataLengkap = "generic/mikro/hotprospek/datalengkap/inquireDataLengkap";
        public static final String sendDataLengkap = "generic/mikro/hotprospek/datalengkap/updateDataLengkap";

        public static final String inquiryHistory = "generic/mikro/hotprospek/history/history";

        public static final String inquirySektorEkonomi = "generic/mikro/hotprospek/datapembiayaan/inquireDataPembiayaan";
        public static final String sendDataSektorEkonomi = "generic/mikro/hotprospek/datapembiayaan/updateDataPembiayaan";

        public static final String hitungRPC = "generic/mikro/hotprospek/rpc/hitungRPC";
        public static final String inquiryRPC = "generic/mikro/hotprospek/rpc/inquireRPC";

        public static final String inquiryKelengkapanDokumen = "generic/mikro/hotprospek/kelengkapandokumen/inquireKelengkapanDokumen";
        public static final String sendKelengkapanDokumen = "generic/mikro/hotprospek/kelengkapandokumen/updateKelengkapanDokumen";


        public static final String cekDHN = "generic/mikro/hotprospek/prescreening/checkDHN";
        public static final String cekDukcapil = "generic/mikro/hotprospek/prescreening/checkDUKCAPIL";
        public static final String cekSlik = "generic/mikro/hotprospek/prescreening/checkSLIK";
        public static final String cekSikp = "generic/mikro/hotprospek/prescreening/checkSIKP";
        public static final String inquiryPrescreening = "generic/mikro/hotprospek/prescreening/inquirePrescreening";
        public static final String sendPrescreening = "generic/mikro/hotprospek/prescreening/updatePrescreening";
        public static final String downloadSlik = "generic/mikro/hotprospek/prescreening/downloadSLIK";
        public static final String downloadSlikPasangan = "generic/mikro/hotprospek/prescreening/downloadSLIKPasangan";
        public static final String inquiryRemaksSlik = "generic/mikro/hotprospek/memosales/inquiryMemosales";
        public static final String sendRemaksSlik = "generic/mikro/hotprospek/memosales/updateMemosales";

        public static final String cekRekomendasi = "generic/mikro/hotprospek/lkn/validateLKN";
        public static final String sendLkn = "generic/mikro/hotprospek/lkn/updateLKN";
        public static final String inquiryLKN = "generic/mikro/hotprospek/lkn/inquireLKN";

        public static final String inquiryScoring = "generic/mikro/hotprospek/scoringtanpajaminan/inquireScoring";
        public static final String sendScoring = "generic/mikro/hotprospek/scoringtanpajaminan/updateScoring";

        public static final String sendPutusanMikro = "generic/mikro/hotprospek/kirimPutusan/KirimPutusanMikro";
        public static final String sendPutusanMikroDeviasi = "generic/mikro/hotprospek/kirimPutusan/kirimPutusanDeviasiMikro";



        public static final String listAgunan = "generic/mikro/hotprospek/agunan/CariDataAgunan_Pengikatan";
        public static final String listAgunanAll = "generic/mikro/hotprospek/agunan/cekDataAgunan_Existing";


        public static final String inquiryAgunanTanahBangunan = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_2";
        public static final String sendAgunanTanahBangunan = "generic/mikro/hotprospek/agunan/saveDataAgunan";
        public static final String listJenisBangunan = "generic/mikro/hotprospek/agunan/listJenisBangunan";
        public static final String searchPasar = "generic/mikro/hotprospek/agunan/pencarianNamaPasar";

        public static final String inquiryInfoAgunan = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Pengikatan";
        public static final String deleteAgunan= "generic/mikro/hotprospek/agunan/HapusDataAgunan";
        public static final String setPengikatan= "generic/mikro/hotprospek/agunan/inquiryDataAgunan_SetPengikatan";
        public static final String ikatAgunan= "generic/mikro/hotprospek/agunan/SavePengikatan";
        public static final String jenisKlasifikasi= "generic/mikro/hotprospek/agunan/jenisKlasifikasi";
        public static final String saveAgunanTerikat= "generic/mikro/hotprospek/agunan/SaveAgunanTerikat";

        public static final String saveTanahKosong = "generic/mikro/hotprospek/agunan/saveDataAgunan_Tanahkosong";
        public static final String inquiryAgunanTanahKosong = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Tanahkosong";

        public static final String inquiryAgunanKios = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Kios";
        public static final String sendAgunanKios = "generic/mikro/hotprospek/agunan/saveDataAgunan_Kios";

        public static final String inquiryAgunanDeposito = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Deposito";
        public static final String sendAgunanDeposito = "generic/mikro/hotprospek/agunan/saveDataAgunan_Deposito";

        public static final String inquiryAgunanKendaraan = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Kendaraan";
        public static final String sendAgunanKendaraan = "generic/mikro/hotprospek/agunan/saveDataAgunan_Kendaraan";

        //multifaedahmikro
//        public static final String listHotprospekKmg = "generic/mikro/konsumer/kmg/hotprospek/listHotProspek";
        public static final String inquiryHotprospekKmg = "generic/mikro/konsumer/kmg/hotprospek/inquireHotprospek";
//        public static final String sendDataHotprospekKmg = "generic/mikro/konsumer/kmg/hotprospek/updateHotprospek";
        public static final String rejectHotprospekKmg = "generic/mikro/konsumer/kmg/hotprospek/tolakHotprospek";

        public static final String listHotprospekKmg = "generic/mikro/konsumer/kmg/hotprospek/listHotProspek";
        public static final String sendDataHotprospekKmg = "generic/mikro/konsumer/kmg/hotprospek/updateHotprospek";

//        public static final String inquiryDataLengkapKmg = "generic/mikro/konsumer/kmg/hotprospek/datalengkap/inquireDataLengkap";
        public static final String cekNikPasangan = "generic/mikro/konsumer/kmg/hotprospek/inquiryPasangan";
//        public static final String sendDataLengkapKmg = "generic/mikro/konsumer/kmg/hotprospek/datalengkap/updateDataLengkap";

        public static final String inquiryDataLengkapKmg = "generic/mikro/konsumer/kmg/hotprospek/datalengkap/inquireDataLengkap";
        public static final String sendDataLengkapKmg = "generic/mikro/konsumer/kmg/hotprospek/datalengkap/updateDataLengkap";

//        public static final String cekDHNKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/checkDHN";
//        public static final String cekDukcapilKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/checkDUKCAPIL";
//        public static final String cekSlikKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/checkSLIK";
        public static final String cekSikpKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/checkSIKP";
//        public static final String inquiryPrescreeningKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/inquirePrescreening";
//        public static final String sendPrescreeningKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/updatePrescreening";
//        public static final String downloadSlikKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/downloadSLIK";
//        public static final String downloadSlikPasanganKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/downloadSLIKPasangan";
//        public static final String inquiryRemaksSlikKmg = "generic/mikro/konsumer/kmg/hotprospek/memosales/inquiryMemosales";
//        public static final String sendRemaksSlikKmg = "generic/mikro/konsumer/kmg/hotprospek/memosales/updateMemosales";

        public static final String cekDHNKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/checkDHN";
        public static final String cekDukcapilKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/checkDUKCAPIL";
        public static final String cekSlikKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/checkSLIK";
        public static final String inquiryPrescreeningKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/inquirePrescreening";
        public static final String sendPrescreeningKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/updatePrescreening";
        public static final String downloadSlikKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/downloadSLIK";
        public static final String downloadSlikPasanganKmg = "generic/mikro/konsumer/kmg/hotprospek/prescreening/downloadSLIKPasangan";
        public static final String inquiryRemaksSlikKmg = "generic/mikro/konsumer/kmg/hotprospek/memosales/inquiryMemosales";
        public static final String sendRemaksSlikKmg = "generic/mikro/konsumer/kmg/hotprospek/memosales/updateMemosales";

        public static final String inquirySektorEkonomiKmg = "generic/mikro/konsumer/kmg/hotprospek/datapby/inquireDataPembiayaan";
        public static final String sendDataSektorEkonomiKmg = "generic/mikro/konsumer/kmg/hotprospek/datapby/updateDataPembiayaan";

//        public static final String inquiryDataFinansialKmg = "generic/mikro/konsumer/kmg/hotprospek/datafinansial/inquiryLoadPrescoring";
//        public static final String simpanDataFinansialKmg = " generic/mikro/konsumer/kmg/hotprospek/datafinansial/simpanDataFinansial";
//        public static final String validasiDataFinansialKmg = "generic/mikro/konsumer/kmg/hotprospek/datafinansial/validasiPlafond";

        public static final String inquiryDataFinansialKmg = "generic/mikro/konsumer/kmg/hotprospek/datafinansial/inquiryLoadPrescoring";
        public static final String simpanDataFinansialKmg = " generic/mikro/konsumer/kmg/hotprospek/datafinansial/simpanDataFinansial";
        public static final String validasiDataFinansialKmg = "generic/mikro/konsumer/kmg/hotprospek/datafinansial/validasiPlafond";

        public static final String listAsuransi = "generic/mikro/konsumer/kmg/hotprospek/datafinansial/ddlAsuransiPenjaminan";
        public static final String inquiryIjk = "generic/mikro/konsumer/kmg/hotprospek/datafinansial/inquiryIjkJamsyar";

        public static final String inquiryScoringKmg = "generic/mikro/konsumer/kmg/hotprospek/scoring/inquireScoring";
        public static final String updateScoringKmg = "generic/mikro/konsumer/kmg/hotprospek/scoring/updateScoring";

        public static final String inquiryKelengkapanDokumenKmg = "generic/mikro/konsumer/kmg/hotprospek/kelengkapandokumen/inquireKelengkapanDokumen";
        public static final String sendKelengkapanDokumenKmg = "generic/mikro/konsumer/kmg/hotprospek/kelengkapandokumen/updateKelengkapanDokumen";

        public static final String inquiryHistoryKmg = "generic/mikro/konsumer/kmg/hotprospek/history/history";

        public static final String listDeviasiKmg = "generic/mikro/konsumer/kmg/hotprospek/kirimPutusan/listDeviasiMikro";

        public static final String sendPutusanMikroKmg = "generic/mikro/konsumer/kmg/hotprospek/kirimPutusan/KirimPutusan";
        public static final String sendPutusanDeviasiKmg = "generic/mikro/konsumer/kmg/hotprospek/kirimPutusan/kirimPutusanDeviasiMikro";
        public static final String cariDepositoAmanah = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_DepositoT24";

    }

    public class hotprospekNos {
        public static final String listHotprospek = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/ListHotProspek";
        public static final String inquiryHotprospek = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/inquireHotprospek";
        public static final String sendDataHotprospek = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/hotprospek/updateHotprospek";
        public static final String rejectHotprospek = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/hotprospek/tolakHotprospek";

        public static final String inquiryDataLengkap = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/InquiryDataLengkap";
        public static final String sendDataLengkap = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/updateDataLengkap";

        public static final String inquiryHistory = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/history";

        public static final String inquirySektorEkonomi = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/inquiryDataPembiayaan";
        public static final String sendDataSektorEkonomi = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/updateDataPembiayaan";

        public static final String hitungRPC = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/hitungRPC";
        public static final String inquiryRPC = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/hitungRPC";

        public static final String inquiryKelengkapanDokumen = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/inquireKelengkapanDokumen";
        public static final String sendKelengkapanDokumen = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/updateKelengkapanDokumen";


        public static final String cekDHN = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/CekDHN";
        public static final String cekDukcapil = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/CekDukcapil";
        public static final String cekSlik = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/CekSlik";
        public static final String cekSikp = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/cekSIKP";
        public static final String inquiryPrescreening = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/inquirePrescreening";
        public static final String sendPrescreening = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/UpdatePreScreening";
        public static final String downloadSlik = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/Aplikasi/downloadSLIKPDF";
        public static final String downloadSlikPasangan = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/Aplikasi/downloadSLIKPDFPasangan";
        public static final String inquiryRemaksSlik = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/InquiryMemosales";
        public static final String sendRemaksSlik = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/UpdateMemosales";

        public static final String cekRekomendasi = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/hotprospek/lkn/validateLKN";
        public static final String sendLkn = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/hotprospek/lkn/updateLKN";
        public static final String inquiryLKN = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/hotprospek/lkn/inquireLKN";

        public static final String hitungScoring = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/scoring/hitungScoring";
        public static final String inquiryScoring = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/scoring/hitungScoring";

        public static final String sendPutusanMikro = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/KirimPutusan/KirimPutusanMikro";
        public static final String sendPutusanMikroDeviasi = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/KirimPutusanDeviasiMikro";



        public static final String listAgunan = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/pengikatanagunan/list";
        public static final String listAgunanAll = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunan/list";

        public static final String listJenisBangunan = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunantanahbangunan/jenisBangunanBrins";
        public static final String searchPasar = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/pasarbrins/list";

        public static final String inquiryInfoAgunan = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/pengikatanagunan/inquiry";
        public static final String deleteAgunan= "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/pengikatanagunan/hapus";
        public static final String setPengikatan= "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/pengikatanagunan/hitung";
        public static final String ikatAgunan= "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/pengikatanagunan/save";
        public static final String jenisKlasifikasi= "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunan/JenisKlasifikasi";
        public static final String saveAgunanTerikat= "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/rekomendasiapraisal/save";

        //ini belum dipake, karena belum butuh inquiry ini
        public static final String inquiryIkatanAgunan= "rekomendasiapraisal/inquiry";


        //AGUNANS
        public static final String inquiryAgunanTanahBangunan = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunantanahbangunan/inquiry";
        public static final String sendAgunanTanahBangunan = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunantanahbangunan/save";

        public static final String saveTanahKosong = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunantanahkosong/save";
        public static final String inquiryAgunanTanahKosong = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunantanahkosong/inquiry";

        public static final String inquiryAgunanKios = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunankios/inquiry";
        public static final String sendAgunanKios = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunankios/save";

        public static final String inquiryAgunanKendaraan = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunankendaraan/inquiry";
        public static final String sendAgunanKendaraan = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunankendaraan/save";

        public static final String cariDepositoAmanah = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/AgunanDepositoT24/Inquiry";

        public static final String inquiryAgunanDeposito = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunandeposito/inquiry";
        public static final String sendAgunanDeposito = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/agunandeposito/save";


    }

    public class approved{
        public static final String listApproved = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/listApproved";
        public static final String listApprovedKmg = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/listApproved";
    }

    public class rejected{
        public static final String listRejected = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/listDitolak";
        public static final String listRejectedKmg = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/listDitolak";
    }

    public class ceknasabah{
        public static final String cekNasabah = "generic/mikro/pipeline/cekNasabah";
    }

    public class ceknasabahNos{
        public static final String cekNasabah = "CekNasabah";
    }

    public class foto{
        public static final String urlPhoto = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/GetImage?ImgID=";
    }

    public class dokumen {
        public static final String urlPdf = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/generic/getPdf/";
        public static final String uploadFile = "/nos-backend/NOS_MIKRO_PROC/rest/API_NOS/generic/uploadFile";
    }

    public class monitoring{
        //MONITORING
        public static final String listMonitoringNasabah = "generic/monitor/listNasabah";
        public static final String listMonitoringNasabahNpf = "generic/monitor/listNasabahNpf";
        public static final String getSaldoNasabah = "generic/getSaldoNasabah";
        public static final String rankingAoTop = "generic/monitor/getRankingAoTop";
        public static final String rankingAoBottom = "generic/monitor/getRankingAoWorst";
        public static final String getRiwayatMutasi = "generic/monitor/getRiwayatMutasi";
        public static final String getRataRata = "generic/monitor/getRatarata";
    }
}



