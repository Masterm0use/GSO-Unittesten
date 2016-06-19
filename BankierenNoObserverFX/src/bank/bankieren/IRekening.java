package bank.bankieren;

import java.io.Serializable;
import observer.IRemotePublisher;

public interface IRekening extends Serializable, IRemotePublisher {
  int getNr();
  Money getSaldo();
  IKlant getEigenaar();
  int getKredietLimietInCenten();
}

