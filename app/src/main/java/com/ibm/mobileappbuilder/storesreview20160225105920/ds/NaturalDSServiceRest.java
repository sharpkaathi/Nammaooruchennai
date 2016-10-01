
package com.ibm.mobileappbuilder.storesreview20160225105920.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface NaturalDSServiceRest{

	@GET("/app/57ef5bd59d17e00300d4d46e/r/naturalDS")
	void queryNaturalDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<NaturalDSItem>> cb);

	@GET("/app/57ef5bd59d17e00300d4d46e/r/naturalDS/{id}")
	void getNaturalDSItemById(@Path("id") String id, Callback<NaturalDSItem> cb);

	@DELETE("/app/57ef5bd59d17e00300d4d46e/r/naturalDS/{id}")
  void deleteNaturalDSItemById(@Path("id") String id, Callback<NaturalDSItem> cb);

  @POST("/app/57ef5bd59d17e00300d4d46e/r/naturalDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<NaturalDSItem>> cb);

  @POST("/app/57ef5bd59d17e00300d4d46e/r/naturalDS")
  void createNaturalDSItem(@Body NaturalDSItem item, Callback<NaturalDSItem> cb);

  @PUT("/app/57ef5bd59d17e00300d4d46e/r/naturalDS/{id}")
  void updateNaturalDSItem(@Path("id") String id, @Body NaturalDSItem item, Callback<NaturalDSItem> cb);

  @GET("/app/57ef5bd59d17e00300d4d46e/r/naturalDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57ef5bd59d17e00300d4d46e/r/naturalDS")
    void createNaturalDSItem(
        @Part("data") NaturalDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<NaturalDSItem> cb);
    
    @Multipart
    @PUT("/app/57ef5bd59d17e00300d4d46e/r/naturalDS/{id}")
    void updateNaturalDSItem(
        @Path("id") String id,
        @Part("data") NaturalDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<NaturalDSItem> cb);
}

