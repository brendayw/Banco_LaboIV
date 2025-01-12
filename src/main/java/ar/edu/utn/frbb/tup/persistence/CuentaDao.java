package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.cuenta.CuentaNoExisteException;
import ar.edu.utn.frbb.tup.persistence.entity.CuentaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CuentaDao  extends AbstractBaseDao{

    @Override
    protected String getEntityName() {
        return "CUENTA";
    }

    public Cuenta find(long id) {
        if(getInMemoryDatabase().get(id) == null){
            return null;
        }
        Cuenta cuenta = ((CuentaEntity) getInMemoryDatabase().get(id)).toCuenta();
        return cuenta;
    }

    public void save(Cuenta cuenta) {
        CuentaEntity entity = new CuentaEntity(cuenta);
        getInMemoryDatabase().put(entity.getId(), entity);
    }

    public List<Cuenta> buscarCuentasByCliente(long dni) {
        List<Cuenta> cuentasDelCliente = new ArrayList<>();
        for (Object object: getInMemoryDatabase().values()) {
            CuentaEntity cuenta = ((CuentaEntity) object);
            if (cuenta.toCuenta().getDniTitular() == dni) {
                cuentasDelCliente.add(cuenta.toCuenta());
            }
        }
        return cuentasDelCliente;
    }

    public Cuenta update(Cuenta cuenta) throws CuentaNoExisteException {
        Cuenta actualizado = find(cuenta.getNumeroCuenta());
        if (actualizado != null) {
            if (cuenta.getBalance() != 0.0) {
                actualizado.setBalance(cuenta.getBalance());
            }
            System.out.println("Datos actualizados con exito.");
        } else {
            throw new CuentaNoExisteException("Cuenta no encontrada.");
        }
        return actualizado;
    }
}