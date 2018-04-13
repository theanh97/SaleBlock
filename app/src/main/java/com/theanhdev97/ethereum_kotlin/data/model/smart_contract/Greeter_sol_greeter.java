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
public class Greeter_sol_greeter extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b6040516103b13803806103b1833981016040528080519091019050600081805161003d929160200190610044565b50506100df565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061008557805160ff19168380011785556100b2565b828001600101855582156100b2579182015b828111156100b2578251825591602001919060010190610097565b506100be9291506100c2565b5090565b6100dc91905b808211156100be57600081556001016100c8565b90565b6102c3806100ee6000396000f30060606040526004361061004b5763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663cfae32178114610050578063d28c25d4146100da575b600080fd5b341561005b57600080fd5b61006361012d565b60405160208082528190810183818151815260200191508051906020019080838360005b8381101561009f578082015183820152602001610087565b50505050905090810190601f1680156100cc5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156100e557600080fd5b61012b60046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506101d695505050505050565b005b6101356101ed565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156101cb5780601f106101a0576101008083540402835291602001916101cb565b820191906000526020600020905b8154815290600101906020018083116101ae57829003601f168201915b505050505090505b90565b60008180516101e99291602001906101ff565b5050565b60206040519081016040526000815290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061024057805160ff191683800117855561026d565b8280016001018555821561026d579182015b8281111561026d578251825591602001919060010190610252565b5061027992915061027d565b5090565b6101d391905b8082111561027957600081556001016102835600a165627a7a72305820b1b1a75ee73c7c1ec6d6a8bd262cb395fc8f0e149a910ef449225606c09af20c0029";

    protected Greeter_sol_greeter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Greeter_sol_greeter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> greet() {
        final Function function = new Function("greet", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> changeGreeting(String _greeting) {
        final Function function = new Function(
                "changeGreeting", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Greeter_sol_greeter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(Greeter_sol_greeter.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Greeter_sol_greeter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(Greeter_sol_greeter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static Greeter_sol_greeter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Greeter_sol_greeter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Greeter_sol_greeter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Greeter_sol_greeter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
