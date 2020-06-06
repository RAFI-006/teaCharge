package com.teashop.teacharge.Model;

public class GetTransactionHIstoryParams
{
  public  String from_date;
  public  String to_date;
 public  int  user_id;

 public  void SetFromDate(String from_date)
   {

       this.from_date=from_date;

   }

 public  String getFromDate()
   {
       return from_date;
   }

  public  void SetToDate(String to_date)
    {

        this.to_date=to_date;

    }

   public String getToDate()
    {
        return to_date;
    }

  public  void setUserId(int user_id)
    {
        this.user_id=user_id;
    }

  public  int getUseId()
    {
        return user_id;
    }



}
