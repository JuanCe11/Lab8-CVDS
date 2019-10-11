package edu.eci.cvds.view;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author jcortes
 */

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped

public class NavigationController implements Serializable {
   public String moveToPageAlquiler() {
      return "registroalquiler";
   }
}