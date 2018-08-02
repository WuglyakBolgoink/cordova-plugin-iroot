module.exports = function() {
    return this.opts && this.opts.options && this.opts.options.verbose || typeof this.cmdLine === 'string' && this.cmdLine.indexOf(' -verbose') > -1;
};
