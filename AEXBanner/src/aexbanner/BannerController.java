/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Mastermouse
 */
public class BannerController {
    
        private AEXBanner banner;
	private MockEffectenbeurs effectenbeurs;
	private Timer pollingTimer;

	public BannerController(AEXBanner banner) {

		this.banner = banner;
		this.effectenbeurs = new MockEffectenbeurs();

		// Start polling timer: update banner every two seconds
		pollingTimer = new Timer();
		pollingTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				banner.setKoersen(effectenbeurs.toString());
			}
		}, 16);
	}

	// Stop banner controller
	public void stop() {
		pollingTimer.cancel();
		// Stop simulation timer of effectenbeurs
	}

    void setEffectenbeurs(IEffectenbeurs effectenbeurs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void addListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    IEffectenbeurs getEffectenbeurs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
