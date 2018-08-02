module.exports = function(msg, exception) {
    process.stdout.write('\n[IRoot] ERROR! ' + msg + '\n');
    throw new Error(exception);
};
