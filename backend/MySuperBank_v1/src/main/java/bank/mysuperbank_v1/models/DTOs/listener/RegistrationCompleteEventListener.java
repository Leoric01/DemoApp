package bank.mysuperbank_v1.models.DTOs.listener;

import bank.mysuperbank_v1.event.RegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

    }
}
