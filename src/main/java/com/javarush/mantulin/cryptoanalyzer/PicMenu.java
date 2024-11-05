package com.javarush.mantulin.cryptoanalyzer;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Spec;
import java.io.File;

@Command(name = "cypher", subcommands =
        {CommandLine.HelpCommand.class },
        description = "Caesar cypher command")
public class PicMenu implements Runnable {
    @Spec CommandSpec spec;
    @Command(name = "encrypt", description = "Encrypt from file to file using key")
            void encrypt(
            @Parameters(paramLabel = "", description =
                    "source file with text to encrypt") File src,
            @Parameters(paramLabel = "", description =
                    "dest file which should have encrypted text") File dest,
            @Parameters(paramLabel = "", description =
                    "key for encryption") int key) {
        System.out.println("encrypt");
    }

    @Command(name = "brute force", description = "Decrypt from file to file using brute force") // |3|
            void bruteForce(
            @Parameters(paramLabel = "", description =
                    "source file with encrypted text") File src,
            @Option(names = {"-r",
                    "--representative"}, description = "file with unencrypted  representative text") File representativeFile,
                    @Parameters(paramLabel = "", description =
                    "dest file which should have decrypted text") File dest) {
        // TODO
    }

    @Command(name = "statistical decryption", description =
            "Decrypt from file to file using statistical analysis") // |3|
    void statisticalDecrypt(
            @Parameters(paramLabel = "", description =
                    "source file with encrypted text") File src,
            @Option(names = {"-r",
                    "--representative"}, description = "file with unencrypted representative text") File representativeFile,
                    @Parameters(paramLabel = "", description =
                    "dest file which should have decrypted text") File dest) {
        // TODO
    }

    @Command(name = "decrypt", description = "Decrypt from file to file using statistical analysis") // |3|
            void decrypt(
            @Parameters(paramLabel = "", description =
                    "source file with encrypted text") File src,
            @Parameters(paramLabel = "", description =
                    "dest file which should have decrypted text") File dest,
            @Parameters(paramLabel = "", description =
                    "key for encryption") int key) {
        // TODO
    }

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(),
                "Specify a subcommand");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new PicMenu()).execute(args);
        System.exit(exitCode);
    }
}
