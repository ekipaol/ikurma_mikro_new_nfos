package com.application.bris.ikurma.api.service;

import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseAgunan;
import com.application.bris.ikurma.api.model.ParseResponseArr;
import com.application.bris.ikurma.api.model.ParseResponseArrAgunan;
import com.application.bris.ikurma.api.model.ParseResponseArrAgunanByCif;
import com.application.bris.ikurma.api.model.ParseResponseDataDukcapil;
import com.application.bris.ikurma.api.model.ParseResponseDataInstansi;
import com.application.bris.ikurma.api.model.ParseResponseGmapsV3;
import com.application.bris.ikurma.api.model.request.EmptyRequest;
import com.application.bris.ikurma.api.model.request.ParseResponseSaldo;
import com.application.bris.ikurma.api.model.request.agunan.ReqAgunan;
import com.application.bris.ikurma.api.model.request.agunan.ReqAgunanByCif;
import com.application.bris.ikurma.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma.api.model.request.agunan.ReqCifDeposito;
import com.application.bris.ikurma.api.model.request.agunan.ReqDeleteAgunan;
import com.application.bris.ikurma.api.model.request.agunan.ReqIkatAgunan;
import com.application.bris.ikurma.api.model.request.agunan.ReqInfoAgunan;
import com.application.bris.ikurma.api.model.request.agunan.ReqSaveAgunan;
import com.application.bris.ikurma.api.model.request.agunan.ReqSaveAgunanKendaraan;
import com.application.bris.ikurma.api.model.request.agunan.ReqSaveAgunanTanahKosong;
import com.application.bris.ikurma.api.model.request.agunan.ReqSetPengikatan;
import com.application.bris.ikurma.api.model.request.ceknasabah.cekNasabah;
import com.application.bris.ikurma.api.model.request.general.Activation;
import com.application.bris.ikurma.api.model.request.general.Checkupdate;
import com.application.bris.ikurma.api.model.request.general.ListDeviasi;
import com.application.bris.ikurma.api.model.request.general.ReqFirebase;
import com.application.bris.ikurma.api.model.request.general.ReqRegisterBrisnotif;
import com.application.bris.ikurma.api.model.request.general.ReqUid;
import com.application.bris.ikurma.api.model.request.general.ReqUploadFoto;
import com.application.bris.ikurma.api.model.request.general.home;
import com.application.bris.ikurma.api.model.request.general.login;
import com.application.bris.ikurma.api.model.request.general.searchAddress;
import com.application.bris.ikurma.api.model.request.general.searchListSektorEkonomi;
import com.application.bris.ikurma.api.model.request.general.searchSektorEkonomi;
import com.application.bris.ikurma.api.model.request.hotprospek.KomponenPrescreening;
import com.application.bris.ikurma.api.model.request.hotprospek.Prescreening;
import com.application.bris.ikurma.api.model.request.hotprospek.PrescreeningSikp;
import com.application.bris.ikurma.api.model.request.hotprospek.cekRekomendasi;
import com.application.bris.ikurma.api.model.request.hotprospek.inputAgunanDeposito;
import com.application.bris.ikurma.api.model.request.hotprospek.inputAgunanKios;
import com.application.bris.ikurma.api.model.request.hotprospek.inputHotprospek;
import com.application.bris.ikurma.api.model.request.hotprospek.inputKelengkapanDokumen;
import com.application.bris.ikurma.api.model.request.hotprospek.inputRemaksSlik;
import com.application.bris.ikurma.api.model.request.hotprospek.inputScoring;
import com.application.bris.ikurma.api.model.request.hotprospek.inputSektorEkonomi;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryDataLengkap;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryHistory;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryHotprospek;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryKelengkapanDokumen;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryLkn;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryRPC;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryScoring;
import com.application.bris.ikurma.api.model.request.hotprospek.inquirySektorEkonomi;
import com.application.bris.ikurma.api.model.request.hotprospek.kirimPutusanMikro;
import com.application.bris.ikurma.api.model.request.hotprospek.kirimPutusanMikroDeviasi;
import com.application.bris.ikurma.api.model.request.hotprospek.listHotprospek;
import com.application.bris.ikurma.api.model.request.hotprospek.rejectHotprospek;
import com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro.KonsumerKMGInputKelengkapanDokumen;
import com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro.ReqScoringKmg;
import com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro.SimpanDataFinansial;
import com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro.ValidasiDataFinansialKmg;
import com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro.inputKelengkapanDokumenKmg;
import com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro.inputSektorEkonomiKmg;
import com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro.inquiryIjk;
import com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro.inquiryNikPasangan;
import com.application.bris.ikurma.api.model.request.monitoring.ReqMonitoringNasabah;
import com.application.bris.ikurma.api.model.request.monitoring.ReqRankingAo;
import com.application.bris.ikurma.api.model.request.monitoring.ReqRiwayatMutasi;
import com.application.bris.ikurma.api.model.request.monitoring.ReqSaldoNasabah;
import com.application.bris.ikurma.api.model.request.pipeline.inputPipeline;
import com.application.bris.ikurma.api.model.request.pipeline.inquiryPipeline;
import com.application.bris.ikurma.api.model.request.pipeline.listPipeline;
import com.application.bris.ikurma.api.model.request.pipeline.processRejectPipeline;
import com.application.bris.ikurma.api.model.request.pipeline_multifaedahmikro.KmgInputPipeline;
import com.application.bris.ikurma.api.model.request.pipeline_multifaedahmikro.KmgInquiryPipeline;
import com.application.bris.ikurma.api.model.request.pipeline_multifaedahmikro.inquiryInstansi;
import com.application.bris.ikurma.api.model.request.pipeline_multifaedahmikro.inquiryListKateg;
import com.application.bris.ikurma.api.model.request.pipeline_multifaedahmikro.inquiryProgram;
import com.application.bris.ikurma.api.model.request.pipeline_multifaedahmikro.inquiryTujuan;
import com.application.bris.ikurma.database.pojo.AgunanKendaraanPojo;
import com.application.bris.ikurma.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma.database.pojo.KmgDataLengkapPojo;
import com.application.bris.ikurma.database.pojo.LknPojo;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by PID on 4/5/2019.
 */

public interface ApiInterface {
    @POST(UriApi.general.login)
    Call<ParseResponse> login (@Body login login);

    @POST(UriApi.generalNos.login2)
    Call<ParseResponse> login2 (@Body login login);

    @POST(UriApi.generalNos.home)
    Call<ParseResponse> home (@Body home home);

    @POST(UriApi.generalNos.getProduct)
    Call<ParseResponse> getProduct (@Body EmptyRequest emptyRequest);

    @POST(UriApi.generalNos.getProductAmanah)
    Call<ParseResponse> getProductAmanah (@Body ReqUid ReqUid);

    @POST(UriApi.general.getKategSektorEkonomii)
    Call<ParseResponse> getKategSektorEkonomii (@Body EmptyRequest emptyRequest);

    @POST(UriApi.generalNos.getKategSektorEkonomii)
    Call<ParseResponse> getKategSektorEkonomiiByGroup (@Body searchListSektorEkonomi searchListSektorEkonomi);

    @POST(UriApi.generalNos.searcSektorEkonomi)
    Call<ParseResponse> searcSektorEkonomi (@Body searchSektorEkonomi searchSektorEkonomi);

    @POST(UriApi.generalNos.listDeviasi)
    Call<ParseResponse> listDeviasi (@Body ListDeviasi listDeviasi);

    @POST(UriApi.general.activation)
    Call<ParseResponse> activation (@Body Activation activation);

    @POST(UriApi.general.checkUpdate)
    Call<ParseResponse> checkUpdate (@Body Checkupdate checkupdate);

    @GET(UriApi.general.geocoding+"pretty=1&limit=1")
    Call<ParseResponseGmapsV3> geocoding(@Query("address") String address, @Query("key") String key);

    @GET(UriApi.generalNos.getKategSektorEkonomii)
    Call<ParseResponse> getKategSektorEkonomiNos();

    @POST(UriApi.general.updateFirebase)
    Call<ParseResponse> updateFirebase(@Body ReqFirebase ReqFirebase);

    @POST(UriApi.general.brisnotifRegister)
    Call<ParseResponse> brisnotifRegister(@Body ReqRegisterBrisnotif ReqRegisterBrisnotif);

    /* ************** PIPELINE *************** */
    @POST(UriApi.pipelineNos.listPipeline)
    Call<ParseResponseArr> listPipeline(@Body listPipeline listPipeline);

    @POST(UriApi.pipelineNos.inquiryPipeline)
    Call<ParseResponse> inquiryPipeline(@Body inquiryPipeline inquiryPipeline);

    @POST(UriApi.ceknasabahNos.cekNasabah)
    Call<ParseResponse> cekNasabah(@Body cekNasabah cekNasabah);

    @Multipart
    @POST(UriApi.pipelineNos.uploadFoto)
    Call<ParseResponse> uploadFoto (@Part MultipartBody.Part file);

    @POST(UriApi.pipelineNos.uploadFoto)
    Call<ParseResponse> uploadFotoNos (@Body ReqUploadFoto ReqUploadFoto);

    @POST(UriApi.pipelineNos.sendDataPipeline)
    Call<ParseResponse> sendDataPipeline(@Body inputPipeline inputPipeline);

    @POST(UriApi.pipelineNos.pipelineToHotprospek)
    Call<ParseResponse> pipelineToHotprospek (@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.rejectPipeline)
    Call<ParseResponse> rejectPipeline (@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipelineNos.savePipelineHotprospek)
    Call<ParseResponse> savePipelineHotprospek(@Body inputPipeline inputPipeline);


    @POST(UriApi.generalNos.searchAddress)
    Call<ParseResponseArr> searchAddress (@Body searchAddress searchAddress);

    //Multifaedahmikro

    @POST(UriApi.pipeline.listPipelineKmg)
    Call<ParseResponseArr> listPipelineKmg(@Body listPipeline listPipeline);

    @POST(UriApi.pipeline.inquiryPipelineKmg)
    Call<ParseResponse> inquiryPipelineKmg(@Body KmgInquiryPipeline inquiryPipeline);

    //Purna
    @POST(UriApi.pipeline.listProgram)
    Call<ParseResponseArr> getListProgram (@Body inquiryProgram inquiryProgram);

    @POST(UriApi.pipeline.listInstitusi)
    Call<ParseResponseArr> listInstitusi (@Body inquiryTujuan inquiryTujuan);

    @POST(UriApi.pipeline.listRekananDM)
    Call<ParseResponseArr> getListRekananDM (@Body EmptyRequest emptyRequest);

    @POST(UriApi.pipeline.listKategNasabah)
    Call<ParseResponseArr> getListKategNasabah (@Body inquiryListKateg inquiryListKateg);

    @POST(UriApi.pipeline.getTujuanPenggunaan)
    Call<ParseResponseArr> getTujuanPenggunaan (@Body inquiryTujuan inquiryTujuan);

    @POST(UriApi.pipeline.getListInstansi)
    Call<ParseResponseArr> getListInstansi (@Body inquiryInstansi inquiryInstansi);

    @POST(UriApi.pipeline.sendDataPipelineKmg)
    Call<ParseResponse> sendDataPipelineKmg(@Body KmgInputPipeline inputPipeline);

    @POST(UriApi.pipeline.pipelineToHotprospekKmg)
    Call<ParseResponse> pipelineToHotprospekKmg (@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.rejectPipelineKmg)
    Call<ParseResponse> rejectPipelineKmg (@Body processRejectPipeline processRejectPipeline);

    @POST(UriApi.pipeline.savePipelineHotprospekKmg)
    Call<ParseResponse> savePipelineHotprospekKmg(@Body KmgInputPipeline inputPipeline);


    /* **************** HOTPROSPEK ********************* */
    @POST(UriApi.hotprospekNos.listHotprospek)
    Call<ParseResponseArr> listHotprospek (@Body listHotprospek listHotprospek);

    @POST(UriApi.hotprospekNos.inquiryHotprospek)
    Call<ParseResponse> inquiryHotprospek (@Body inquiryHotprospek inquiryHotprospek);

    @POST(UriApi.hotprospekNos.sendDataHotprospek)
    Call<ParseResponse> sendDataHotprospek (@Body inputHotprospek inputHotprospek);

    @POST(UriApi.hotprospekNos.rejectHotprospek)
    Call<ParseResponse> rejectHotprospek (@Body rejectHotprospek rejectHotprospek);

    @POST(UriApi.hotprospekNos.inquiryDataLengkap)
    Call<ParseResponse> inquiryDataLengkap (@Body inquiryDataLengkap inquiryDataLengkap);

    @POST(UriApi.hotprospekNos.sendDataLengkap)
    Call<ParseResponse> sendDataLengkap (@Body DataLengkapPojo dataLengkapPojo);

    @POST(UriApi.hotprospekNos.inquiryHistory)
    Call<ParseResponse> inquiryHistory (@Body inquiryHistory inquiryHistory);

    @POST(UriApi.hotprospekNos.inquirySektorEkonomi)
    Call<ParseResponse> inquirySektorEkonomi (@Body inquirySektorEkonomi inquirySektorEkonomi);

    @POST(UriApi.hotprospekNos.sendDataSektorEkonomi)
    Call<ParseResponse> sendDataSektorEkonomi (@Body inputSektorEkonomi inputSektorEkonomi);

    @POST(UriApi.hotprospekNos.hitungRPC)
    Call<ParseResponse> hitungRPC(@Body inquiryRPC inquiryRPC);

    @POST(UriApi.hotprospekNos.inquiryRPC)
    Call<ParseResponse> inquiryRPC(@Body inquiryRPC inquiryRPC);

    @POST(UriApi.hotprospekNos.inquiryKelengkapanDokumen)
    Call<ParseResponse> inquiryKelengkapanDokumen (@Body inquiryKelengkapanDokumen inquiryKelengkapanDokumen);

    @POST(UriApi.hotprospekNos.sendKelengkapanDokumen)
    Call<ParseResponse> sendKelengkapanDokumen (@Body inputKelengkapanDokumen inputKelengkapanDokumen);

    @POST(UriApi.hotprospekNos.cekRekomendasi)
    Call<ParseResponse> cekRekomendasi (@Body cekRekomendasi cekRekomendasi);

    @POST(UriApi.hotprospekNos.inquiryLKN)
    Call<ParseResponse> inquiryLKN (@Body inquiryLkn inquiryLkn);

    @POST(UriApi.hotprospekNos.sendLkn)
    Call<ParseResponse> sendLkn (@Body LknPojo lknPojo);


    @POST(UriApi.hotprospekNos.cekDHN)
    Call<ParseResponse> cekDHN (@Body KomponenPrescreening KomponenPrescreening);
    @POST(UriApi.hotprospekNos.cekDukcapil)
    Call<ParseResponse> cekDukcapil (@Body KomponenPrescreening KomponenPrescreening);
    @POST(UriApi.hotprospekNos.cekSlik)
    Call<ParseResponse> cekSlik (@Body KomponenPrescreening KomponenPrescreening);
    @POST(UriApi.hotprospekNos.cekSikp)
    Call<ParseResponse> cekSikp (@Body PrescreeningSikp PrescreeningSikp);

    //pakai inquiry rpc karena dia sama sama request id Aplikasi
    @POST(UriApi.hotprospekNos.inquiryPrescreening)
    Call<ParseResponse> inquiryPrescreening (@Body Prescreening prescreening);
    @POST(UriApi.hotprospekNos.sendPrescreening)
    Call<ParseResponse> sendPrescreening (@Body Prescreening prescreening);
    @POST(UriApi.hotprospekNos.downloadSlik)
    Call<ParseResponse> downloadSlik (@Body Prescreening prescreening);
    @POST(UriApi.hotprospekNos.downloadSlikPasangan)
    Call<ParseResponse> downloadSlikPasangan (@Body Prescreening prescreening);
    @POST(UriApi.hotprospekNos.inquiryRemaksSlik)
    Call<ParseResponse> inquiryRemaksSlik (@Body Prescreening prescreening);
    @POST(UriApi.hotprospekNos.sendRemaksSlik)
    Call<ParseResponse> sendRemaksSlik (@Body inputRemaksSlik inputRemaksSlik);


    @POST(UriApi.hotprospekNos.inquiryScoring)
    Call<ParseResponse> inquiryScoring (@Body inquiryScoring inquiryScoring);
    @POST(UriApi.hotprospekNos.sendScoring)
    Call<ParseResponse> sendScoring (@Body inputScoring inputScoring);

    @POST(UriApi.hotprospekNos.sendPutusanMikro)
    Call<ParseResponse> sendPutusanMikro (@Body kirimPutusanMikro kirimPutusanMikro);

    @POST(UriApi.hotprospekNos.sendPutusanMikroDeviasi)
    Call<ParseResponse> sendPutusanMikroDeviasi (@Body kirimPutusanMikroDeviasi kirimPutusanMikroDeviasi);

    //AGUNAN


    @POST(UriApi.hotprospekNos.listAgunan)
    Call<ParseResponseArrAgunan> listAgunan(@Body ReqAgunan ReqAgunan);

    @POST(UriApi.hotprospekNos.listAgunanAll)
    Call<ParseResponseArrAgunanByCif> listAgunanAll(@Body ReqAgunanByCif ReqAgunanByCif);

    @POST(UriApi.hotprospekNos.inquiryAgunanTanahBangunan)
    Call<ParseResponse> inquiryAgunanTanahBangunan(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospekNos.sendAgunanTanahBangunan)
    Call<ParseResponse> sendAgunanTanahBangunan(@Body AgunanTanahBangunanPojo agunanTanahBangunanPojo);

    @POST(UriApi.hotprospekNos.listJenisBangunan)
    Call<ParseResponseArr> listJenisBangunan (@Body EmptyRequest emptyRequest);

    @POST(UriApi.hotprospekNos.searchPasar)
    Call<ParseResponse> searchPasar (@Body searchAddress searchAddress);

    @POST(UriApi.hotprospekNos.inquiryInfoAgunan)
    Call<ParseResponse> inquiryInfoAgunan(@Body ReqInfoAgunan ReqInfoAgunan);

    @POST(UriApi.hotprospekNos.deleteAgunan)
    Call<ParseResponse> deleteAgunan(@Body ReqDeleteAgunan ReqDeleteAgunan);

    @POST(UriApi.hotprospekNos.setPengikatan)
    Call<ParseResponse> setPengikatan(@Body ReqSetPengikatan ReqSetPengikatan);

    @POST(UriApi.hotprospekNos.ikatAgunan)
    Call<ParseResponse> ikatAgunan(@Body ReqIkatAgunan ReqIkatAgunan);

    @POST(UriApi.hotprospekNos.jenisKlasifikasi)
    Call<ParseResponseArr> jenisKlasifikasi(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.hotprospekNos.saveAgunanTerikat)
    Call<ParseResponse> saveAgunanTerikat(@Body ReqSaveAgunan ReqSaveAgunan);

    @POST(UriApi.hotprospekNos.inquiryAgunanKios)
    Call<ParseResponseArr> inquiryAgunanKios(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospekNos.sendAgunanKios)
    Call<ParseResponse> sendAgunanKios(@Body inputAgunanKios inputAgunanKios);


    @POST(UriApi.hotprospekNos.inquiryAgunanTanahKosong)
    Call<ParseResponseArr> inquiryAgunanTanahKosong(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospekNos.saveTanahKosong)
    Call<ParseResponse> saveTanahKosong(@Body ReqSaveAgunanTanahKosong ReqSaveAgunanTanahKosong);

    @POST(UriApi.hotprospekNos.inquiryAgunanDeposito)
    Call<ParseResponse> inquiryAgunanDeposito(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospekNos.sendAgunanDeposito)
    Call<ParseResponse> sendAgunanDeposito(@Body inputAgunanDeposito inputAgunanDeposito);

    @POST(UriApi.hotprospekNos.inquiryAgunanKendaraan)
    Call<ParseResponse> inquiryAgunanKendaraan(@Body ReqAgunanData reqAgunanData);

    @POST(UriApi.hotprospekNos.sendAgunanKendaraan)
    Call<ParseResponse> sendAgunanKendaraan(@Body AgunanKendaraanPojo agunanKendaraanPojo);

    //Multifaedahmikro belum dipake

    @POST(UriApi.hotprospek.listHotprospekKmg)
    Call<ParseResponseArr> listHotprospekKmg (@Body listHotprospek listHotprospek);

    @POST(UriApi.hotprospek.inquiryHotprospekKmg)
    Call<ParseResponse> inquiryHotprospekKmg (@Body inquiryHotprospek inquiryHotprospek);

    @POST(UriApi.hotprospek.sendDataHotprospekKmg)
    Call<ParseResponse> sendDataHotprospekKmg (@Body inputHotprospek inputHotprospek);

    @POST(UriApi.hotprospek.rejectHotprospekKmg)
    Call<ParseResponse> rejectHotprospekKmg (@Body rejectHotprospek rejectHotprospek);

    @POST(UriApi.hotprospek.inquiryDataLengkapKmg)
    Call<ParseResponse> inquiryDataLengkapKmg (@Body inquiryDataLengkap inquiryDataLengkap);

    @POST(UriApi.hotprospek.cekNikPasangan)
    Call<ParseResponseDataDukcapil> cekNikPasangan (@Body inquiryNikPasangan inquiryNikPasangan);

    @POST(UriApi.hotprospek.sendDataLengkapKmg)
    Call<ParseResponse> sendDataLengkapKmg (@Body KmgDataLengkapPojo kmgDataLengkapPojo);

    @POST(UriApi.hotprospek.cekDHNKmg)
    Call<ParseResponse> cekDHNKmg (@Body KomponenPrescreening KomponenPrescreening);

    @POST(UriApi.hotprospek.cekDukcapilKmg)
    Call<ParseResponse> cekDukcapilKmg (@Body KomponenPrescreening KomponenPrescreening);

    @POST(UriApi.hotprospek.cekSlikKmg)
    Call<ParseResponse> cekSlikKmg (@Body KomponenPrescreening KomponenPrescreening);

    @POST(UriApi.hotprospek.inquiryPrescreeningKmg)
    Call<ParseResponse> inquiryPrescreeningKmg (@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.sendPrescreeningKmg)
    Call<ParseResponse> sendPrescreeningKmg (@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.downloadSlikKmg)
    Call<ParseResponse> downloadSlikKmg (@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.downloadSlikPasanganKmg)
    Call<ParseResponse> downloadSlikPasanganKmg (@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.inquiryRemaksSlikKmg)
    Call<ParseResponse> inquiryRemaksSlikKmg (@Body Prescreening prescreening);

    @POST(UriApi.hotprospek.sendRemaksSlikKmg)
    Call<ParseResponse> sendRemaksSlikKmg (@Body inputRemaksSlik inputRemaksSlik);

    @POST(UriApi.hotprospek.inquirySektorEkonomiKmg)
    Call<ParseResponse> inquirySektorEkonomiKmg (@Body inquirySektorEkonomi inquirySektorEkonomi);

    @POST(UriApi.hotprospek.sendDataSektorEkonomiKmg)
    Call<ParseResponse> sendDataSektorEkonomiKmg (@Body inputSektorEkonomiKmg inputSektorEkonomiKmg);

    //pakai inquiry rpc karena dia sama sama request id Aplikasi
    @POST(UriApi.hotprospek.inquiryDataFinansialKmg)
    Call<ParseResponseDataInstansi> inquiryDataFinansialKmg (@Body inquiryRPC inquiryRPC);

    @POST(UriApi.hotprospek.simpanDataFinansialKmg)
    Call<ParseResponse> simpanDataFinansialKmg (@Body SimpanDataFinansial SimpanDataFinansial);

    @POST(UriApi.hotprospek.validasiDataFinansialKmg)
    Call<ParseResponse> validasiDataFinansialKmg (@Body ValidasiDataFinansialKmg ValidasiDataFinansialKmg);

    @POST(UriApi.hotprospek.listAsuransi)
    Call<ParseResponseArr> listAsuransi (@Body EmptyRequest emptyRequest);

    @POST(UriApi.hotprospek.inquiryIjk)
    Call<ParseResponse> inquiryIjk (@Body inquiryIjk inquiryIjk);


    @POST(UriApi.hotprospek.inquiryScoringKmg)
    Call<ParseResponse> inquiryScoringKmg (@Body ReqScoringKmg ReqScoringKmg);

    @POST(UriApi.hotprospek.updateScoringKmg)
    Call<ParseResponse> updateScoringKmg (@Body ReqScoringKmg ReqScoringKmg);

    @POST(UriApi.hotprospek.inquiryKelengkapanDokumenKmg)
    Call<ParseResponse> inquiryKelengkapanDokumenKmg (@Body inquiryKelengkapanDokumen inquiryKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendKelengkapanDokumenKmg)
    Call<ParseResponse> sendKelengkapanDokumenKonsumerKmg (@Body inputKelengkapanDokumenKmg inputKelengkapanDokumen);

    @POST(UriApi.hotprospek.sendKelengkapanDokumenKmg)
    Call<ParseResponse> sendKelengkapanDokumenKmg (@Body KonsumerKMGInputKelengkapanDokumen inputKelengkapanDokumen);

    @Multipart
    @POST(UriApi.dokumen.uploadFile)
    Call<ParseResponse> uploadFile (@Part MultipartBody.Part file);

    @POST(UriApi.hotprospek.inquiryHistoryKmg)
    Call<ParseResponse> inquiryHistoryKmg (@Body inquiryHistory inquiryHistory);

    @POST(UriApi.hotprospek.listDeviasiKmg)
    Call<ParseResponse> listDeviasiKmg (@Body ListDeviasi listDeviasi);

    @POST(UriApi.hotprospek.sendPutusanMikroKmg)
    Call<ParseResponse> sendPutusanMikroKmg (@Body kirimPutusanMikro kirimPutusanMikro);

    @POST(UriApi.hotprospek.sendPutusanDeviasiKmg)
    Call<ParseResponse> sendPutusanDeviasiKmg (@Body kirimPutusanMikroDeviasi kirimPutusanMikroDeviasi);

    @POST(UriApi.hotprospekNos.cariDepositoAmanah)
    Call<ParseResponse> cariDepositoAmanah (@Body ReqCifDeposito ReqCifDeposito);

    //MONITORING
    @POST(UriApi.monitoring.listMonitoringNasabah)
    Call<ParseResponse> listMonitoringNasabah(@Body ReqMonitoringNasabah ReqMonitoringNasabah);
    @POST(UriApi.monitoring.listMonitoringNasabahNpf)
    Call<ParseResponse> listMonitoringNasabahNpf(@Body ReqMonitoringNasabah ReqMonitoringNasabah);
    @POST(UriApi.monitoring.getSaldoNasabah)
    Call<ParseResponseSaldo> getSaldoNasabah(@Body ReqSaldoNasabah ReqSaldoNasabah);

    @POST(UriApi.monitoring.rankingAoTop)
    Call<ParseResponseArr> getRankingAoTop(@Body ReqRankingAo ReqRankingAo);

    @POST(UriApi.monitoring.rankingAoBottom)
    Call<ParseResponseArr> getRankingAoBottom(@Body ReqRankingAo ReqRankingAo);

    @POST(UriApi.monitoring.getRiwayatMutasi)
    Call<ParseResponse> getRiwayatMutasi(@Body ReqRiwayatMutasi ReqRiwayatMutasi);

    @POST(UriApi.monitoring.getRataRata)
    Call<ParseResponse> getRataRata(@Body EmptyRequest EmptyRequest);



    /* **************** APPROVED ********************* */
    @POST(UriApi.approved.listApproved)
    Call<ParseResponseArr> listApproved (@Body listHotprospek listHotprospek);

    @POST(UriApi.approved.listApprovedKmg)
    Call<ParseResponseArr> listApprovedKmg (@Body listHotprospek listHotprospek);

    /* **************** REJECTED ********************* */
    @POST(UriApi.rejected.listRejected)
    Call<ParseResponseArr> listRejected (@Body listHotprospek listHotprospek);

    @POST(UriApi.rejected.listRejectedKmg)
    Call<ParseResponseArr> listRejectedKmg (@Body listHotprospek listHotprospek);





}
