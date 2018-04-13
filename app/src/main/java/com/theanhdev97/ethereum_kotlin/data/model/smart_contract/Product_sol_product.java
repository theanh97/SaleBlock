package com.theanhdev97.ethereum_kotlin.data.model.smart_contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.1.
 */
public class Product_sol_product extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b60405161082938038061082983398101604052808051820191906020018051820191906020018051820191906020018051820191906020018051909101905060008580516100619291602001906100bc565b5060018480516100759291602001906100bc565b5060028380516100899291602001906100bc565b50600382805161009d9291602001906100bc565b5060048180516100b19291602001906100bc565b505050505050610157565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100fd57805160ff191683800117855561012a565b8280016001018555821561012a579182015b8281111561012a57825182559160200191906001019061010f565b5061013692915061013a565b5090565b61015491905b808211156101365760008155600101610140565b90565b6106c3806101666000396000f3006060604052600436106100a35763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166317d7de7c81146100a85780631a092541146101325780635353a2d8146101455780635ac64c2e1461019857806398d5fdca146101e9578063b26b4f25146101fc578063e61204131461024d578063e76573981461029e578063f3651cbb146102b1578063ff3858ac146102c4575b600080fd5b34156100b357600080fd5b6100bb610315565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156100f75780820151838201526020016100df565b50505050905090810190601f1680156101245780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561013d57600080fd5b6100bb6103be565b341561015057600080fd5b61019660046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061043195505050505050565b005b34156101a357600080fd5b61019660046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061044895505050505050565b34156101f457600080fd5b6100bb61045b565b341561020757600080fd5b61019660046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506104ce95505050505050565b341561025857600080fd5b61019660046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506104e195505050505050565b34156102a957600080fd5b6100bb6104f4565b34156102bc57600080fd5b6100bb610567565b34156102cf57600080fd5b61019660046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506105da95505050505050565b61031d6105ed565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103b35780601f10610388576101008083540402835291602001916103b3565b820191906000526020600020905b81548152906001019060200180831161039657829003601f168201915b505050505090505b90565b6103c66105ed565b60028054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103b35780601f10610388576101008083540402835291602001916103b3565b60008180516104449291602001906105ff565b5050565b60048180516104449291602001906105ff565b6104636105ed565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103b35780601f10610388576101008083540402835291602001916103b3565b60018180516104449291602001906105ff565b60028180516104449291602001906105ff565b6104fc6105ed565b60048054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103b35780601f10610388576101008083540402835291602001916103b3565b61056f6105ed565b60038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103b35780601f10610388576101008083540402835291602001916103b3565b60038180516104449291602001906105ff565b60206040519081016040526000815290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061064057805160ff191683800117855561066d565b8280016001018555821561066d579182015b8281111561066d578251825591602001919060010190610652565b5061067992915061067d565b5090565b6103bb91905b8082111561067957600081556001016106835600a165627a7a72305820a55dd0d2399b17c873e5de22a329d0f32b1956d3639f04550b7ddf58c1b822470029";

    protected Product_sol_product(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Product_sol_product(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> getName() {
        final Function function = new Function("getName", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getDescription() {
        final Function function = new Function("getDescription", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> changeName(String _name) {
        final Function function = new Function(
                "changeName", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeDateCreated(String _dateCreated) {
        final Function function = new Function(
                "changeDateCreated", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_dateCreated)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getPrice() {
        final Function function = new Function("getPrice", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> changePrice(String _price) {
        final Function function = new Function(
                "changePrice", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeDescription(String _description) {
        final Function function = new Function(
                "changeDescription", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_description)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getDateCreated() {
        final Function function = new Function("getDateCreated", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getImage() {
        final Function function = new Function("getImage", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> changeImage(String _image) {
        final Function function = new Function(
                "changeImage", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_image)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Product_sol_product> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _name, String _price, String _description, String _image, String _dateCreated) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_price), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.Utf8String(_image), 
                new org.web3j.abi.datatypes.Utf8String(_dateCreated)));
        return deployRemoteCall(Product_sol_product.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Product_sol_product> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _name, String _price, String _description, String _image, String _dateCreated) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_price), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.Utf8String(_image), 
                new org.web3j.abi.datatypes.Utf8String(_dateCreated)));
        return deployRemoteCall(Product_sol_product.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static Product_sol_product load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Product_sol_product(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Product_sol_product load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Product_sol_product(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
