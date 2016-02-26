package com.hujh;

public class CallbackFetcherExample {
  public static void main(String[] args) {
    new Worker().doWork();
  }
}

interface FetchCallback {
  void onData(Data data);

  void onError(Throwable cause);
}

interface Fetcher {
  void fetchData(FetchCallback callback);
}

class Worker {
  Data data = new Data();

  public void doWork() {
    Fetcher fetcher = new Fetcher() {
      public void fetchData(FetchCallback callback) {
        System.out.println("start fetch data...");
        callback.onData(data);
      }
    };
    fetcher.fetchData(new FetchCallback() {
      public void onData(Data data) { // call if data is fetched without error
        System.out.println("Data received: " + data);
      }

      public void onError(Throwable cause) { // call if error is received during
                                             // fetch
        System.err.println("An error accour: " + cause.getMessage());
      }
    });
  }
}

class Data {

}